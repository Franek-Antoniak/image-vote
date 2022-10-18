package com.highschool.image.vote.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/")
	public ResponseEntity<String> homepage() {
		return userService.getHomePage();
	}

	@GetMapping("/user/creator")
	public ResponseEntity<String> creator() {
		return userService.getCreatorIfUserCanUpload();
	}

	@GetMapping("/admin/settings")
	public ResponseEntity<String> settings() {
		return userService.getSettings();
	}
}
