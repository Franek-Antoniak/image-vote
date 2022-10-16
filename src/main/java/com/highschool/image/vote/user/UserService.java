package com.highschool.image.vote.user;


import com.highschool.image.vote.error.DataBaseSelectException;
import com.highschool.image.vote.freemarker.FreeMarkerService;
import com.highschool.image.vote.image.Image;
import com.highschool.image.vote.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

	private final WebUserService webUserService;
	private final UserRepository userRepository;
	private final Supplier<Exception> DBExceptionSup = () -> new DataBaseSelectException(
			"After all operations there is null in Select");
	private final ImageRepository imageRepository;
	private final FreeMarkerService freeMarkerService;

	public void addAuthorshipAndCreate() {
		User user = getUserOrElseCreate();
		userRepository.save(user);
	}

	public User getUserOrElseCreate() {
		Optional<User> optionalUser = userRepository.findByName(webUserService.getUserNameId());
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		createUser();
		return userRepository.findByName(webUserService.getUserNameId()).get();
	}

	public void createUser() {
		User user = new User();
		String name = webUserService.getUserNameId();
		user.setName(name);
		userRepository.save(user);
	}

	public ResponseEntity<String> getCreatorIfUserCanUpload() {
		return freeMarkerService.getResponseEntityHTML("creator.ftl");
	}

	public ResponseEntity<String> getHomePage() {
		User user = getUserOrElseCreate();
		List<Image> imageList = imageRepository.findAll();
		if (user.getVotes() == 0) {
			return freeMarkerService.getResponseEntityHTML("error-403.ftl", HttpStatus.FORBIDDEN);
		}
		return freeMarkerService.getResponseEntityHTML("homepage.ftl", new String[]{"user", "imageList"},
				new Object[]{user, imageList});
	}

	public ResponseEntity<String> getSettings() {
		return freeMarkerService.getResponseEntityHTML("settings.ftl");
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}
}
