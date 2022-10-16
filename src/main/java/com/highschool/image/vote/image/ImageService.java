package com.highschool.image.vote.image;

import com.highschool.image.vote.error.IllegalCallException;
import com.highschool.image.vote.freemarker.FreeMarkerService;
import com.highschool.image.vote.user.User;
import com.highschool.image.vote.user.UserService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

	private static final String imagesDirName = "images";
	private final UserService userService;
	private final ImageRepository imageRepository;
	private final FreeMarkerService freeMarkerService;

	public void saveImage(@NotNull MultipartFile imageFile) throws IOException {
		byte[] imageBytes = imageFile.getBytes();
		String newFileName = randomString().concat(".png");
		Path folderPath = Paths.get(imagesDirName);
		if (!Files.exists(folderPath)) {
			Files.createDirectory(folderPath);
		}
		Path filePath = Paths.get(folderPath + "/" + newFileName);
		Files.write(filePath, imageBytes);
		Image image = new Image();
		image.setAuthor(userService.getUserOrElseCreate());
		image.setPath("/" + filePath);
		imageRepository.save(image);
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
		List<Image> imageList = imageRepository.findAllByOrderByVotesDesc();
		return freeMarkerService.getResponseEntityHTML("results.ftl", "imageList", imageList);
	}

	public void vote(String uniqueId) throws IllegalCallException {
		Optional<Image> optionalImage = imageRepository.findByUniqueId(UUID.fromString(uniqueId));
		if (optionalImage.isEmpty()) {
			return;
		}
		Image image = optionalImage.get();
		User user = userService.getUserOrElseCreate();
		if (!user.canVote()) {
			throw new IllegalCallException("Too many votes!");
		}
		if (user.getHashSet()
		        .contains(image.getUniqueId())) {
			throw new IllegalCallException("You already voted for this image!");
		}
		if (image.getAuthor()
		             .equals(user)) {
			throw new IllegalCallException("You can't vote for your own image!");
		}
		image.setVotes(image.getVotes() + 1);
		user.setVotes(user.getVotes() - 1);
		user.getHashSet()
		    .add(image.getUniqueId());
		userService.saveUser(user);
		imageRepository.save(image);
	}
}
