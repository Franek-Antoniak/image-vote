package com.picscore.image;

import com.picscore.error.IllegalCallException;
import com.picscore.freemarker.FreeMarkerService;
import com.picscore.image.utils.ImageSaverUtils;
import com.picscore.user.User;
import com.picscore.user.UserService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageService {
	private final UserService userService;
	private final ImageRepository imageRepository;
	private final FreeMarkerService freeMarkerService;
	private final ImageSaverUtils imageSaverUtils;

	/*
	 * Upload image to server by:
	 * 1. Compressing image
	 * 2. Saving image to server
	 * 3. Saving image object with path file to database
	 * @param imageFile - image file
	 * @throws IOException - if no such path/file (Internal server error)
	 */
	public void uploadImage(@NotNull MultipartFile imageFile) throws IOException {
		String newFileName = imageSaverUtils.saveImage(imageFile);
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

	public ResponseEntity<String> getAllImagesWithResult() {
		List<Image> imageList = imageRepository.findAll();
		// - for descending order
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

