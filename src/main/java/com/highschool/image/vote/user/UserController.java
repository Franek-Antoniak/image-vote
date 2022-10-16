package com.highschool.image.vote.user;

import com.highschool.image.vote.cleaner.CleanerService;
import com.highschool.image.vote.freemarker.FreeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final CleanerService cleanerService;

	@GetMapping("/")
	public ResponseEntity<String> homepage() throws Exception {
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

	@PatchMapping("/admin/delete/data")
	public ResponseEntity<String> deleteAllData() {
		cleanerService.clearDataBase();
		return ResponseEntity.status(HttpStatus.OK)
		                     .build();
	}
}
