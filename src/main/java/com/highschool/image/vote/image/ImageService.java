package com.highschool.image.vote.image;

import com.highschool.image.vote.error.IllegalCallException;
import com.highschool.image.vote.freemarker.FreeMarkerService;
import com.highschool.image.vote.user.User;
import com.highschool.image.vote.user.UserService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageService {
	private final UserService userService;
	private final ImageRepository imageRepository;
	private final FreeMarkerService freeMarkerService;

	public void saveImage(@NotNull MultipartFile imageFile) throws IOException {
		InputStream input = imageFile.getInputStream();
		String newFileName = randomString().concat(".png");
		Path folderPath = Paths.get("images");
		if (!Files.exists(folderPath)) {
			Files.createDirectory(folderPath);
		}
		Path filePath = Paths.get(folderPath + "/" + newFileName);
		OutputStream output = Files.newOutputStream(filePath);
		IOUtils.copy(input, output);
		output.close();
		input.close();
		User user = userService.getUserOrElseCreate();
		Image image = Image.builder()
		                   .author(user)
		                   .fileName(newFileName)
		                   .build();
		user.getUploadedImages()
		    .add(image);
		imageRepository.save(image);
		userService.saveUser(user);
	}

	public String randomString() {
		int leftLimit = 'a';
		int rightLimit = 'z';
		int targetStringLength = 20;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1)
		             .limit(targetStringLength)
		             .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		             .toString();
	}

	public ResponseEntity<String> getAllImagesWithResult() {
		List<Image> imageList = imageRepository.findAll();
		// - for ascending order
		imageList.sort(Comparator.comparing(x -> -x.getVoters()
		                                           .size()));
		return freeMarkerService.getResponseEntityHTML("results.ftl", "imageList", imageList);
	}

	public void vote(String uniqueId) throws IllegalCallException {
		Optional<Image> optionalImage = imageRepository.findByUniqueId(UUID.fromString(uniqueId));
		if (optionalImage.isEmpty()) {
			return;
		}
		Image image = optionalImage.get();
		User user = userService.getUserOrElseCreate();
		if (user.getVotes()
		        .size() == 3) {
			throw new IllegalCallException("Too many votes!");
		}
		if (user.getVotes()
		        .contains(image)) {
			throw new IllegalCallException("You already voted for this image!");
		}
		if (image.getAuthor()
		         .equals(user)) {
			throw new IllegalCallException("You can't vote for your own image!");
		}
		user.getVotes()
		    .add(image);
		image.getVoters()
		     .add(user);
		userService.saveUser(user);
		imageRepository.save(image);
	}

	public void deleteImage(String uniqueId) {
		User user = userService.getUserOrElseCreate();
		Image image = imageRepository.findByAuthorAndUniqueId(user, UUID.fromString(uniqueId))
		                             .orElseThrow(() -> new AccessDeniedException(
				                             "You don't have permission to delete this image!"));
		user.getUploadedImages()
		    .remove(image);
		image.getVoters()
		     .forEach(x -> x.getVotes()
		                    .remove(image));
		userService.saveUser(user);
		imageRepository.delete(image);
		File[] files = Paths.get("images")
		                    .toFile()
		                    .listFiles();
		if (files != null) {
			Arrays.stream(files)
			      .filter(file -> file.getName()
			                          .equals(image.getFileName()))
			      .findFirst()
			      .ifPresent(File::delete);
		}
	}

	public void unVote(String uniqueId) throws IllegalCallException {
		User user = userService.getUserOrElseCreate();
		Image image = imageRepository.findByUniqueId(UUID.fromString(uniqueId))
		                             .orElseThrow(() -> new IllegalCallException("Image not found!"));
		if (!user.getVotes()
		         .contains(image)) {
			throw new IllegalCallException("You didn't vote for this image!");
		}
		user.getVotes()
		    .remove(image);
		image.getVoters()
		     .remove(user);
		userService.saveUser(user);
		imageRepository.save(image);
	}
}

