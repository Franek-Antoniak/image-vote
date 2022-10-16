package com.highschool.image.vote.image;


import com.highschool.image.vote.error.IllegalCallException;
import com.highschool.image.vote.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

	private final ImageService imageService;
	private final UserService userService;

	@PostMapping("/user/uploadImage")
	public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
		try {
			userService.addAuthorshipAndCreate();
			imageService.saveImage(imageFile);
		} catch (NullPointerException e) {
			System.out.println("MultipartFile has Null" + e.getMessage());
		} catch (IOException e) {
			System.out.println("No such path/file" + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok("");
	}

	@PatchMapping("/image/vote/{uniqueId}")
	public ResponseEntity<String> vote(@PathVariable String uniqueId) throws Exception {
		try {
			imageService.vote(uniqueId);
		} catch (IllegalCallException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
			                     .body(e.getMessage());
		}
		return ResponseEntity.ok("");
	}

	@GetMapping("/admin/results")
	public ResponseEntity<String> results() {
		return imageService.getAllImagesWithResult();
	}

}
