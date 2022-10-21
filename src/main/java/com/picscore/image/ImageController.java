package com.picscore.image;


import com.picscore.error.IllegalCallException;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageController {
	private final ImageService imageService;

	@PostMapping("/user/images/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("imagesFiles") List<MultipartFile> imagesFiles) {
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.CREATED)
		                                                      .body("Images uploaded successfully");
		try {
			for (MultipartFile imagesFile : imagesFiles) {
				try {
					imageService.uploadImage(imagesFile);
				} catch (IOException e) {
					System.err.println("Error while uploading image: " + e.getMessage());
					responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					                               .body("Internal server error. Some files were not uploaded");
				}
			}
		} catch (NullPointerException e) {
			System.out.println("MultipartFile is null" + e.getMessage());
			return new ResponseEntity<>("MultipartFile is null", HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@Transactional
	@DeleteMapping("/user/image/delete/{uniqueId}")
	public ResponseEntity<String> deleteImage(@PathVariable @NotNull String uniqueId) {
		try {
			imageService.deleteImage(uniqueId);
		} catch (AccessDeniedException e) {
			throw new AccessDeniedException(e.getMessage());
		}
		return ResponseEntity.ok()
		                     .build();
	}


	@PatchMapping("/user/image/vote/{uniqueId}")
	public ResponseEntity<String> vote(@PathVariable String uniqueId) throws Exception {
		try {
			imageService.vote(uniqueId);
		} catch (IllegalCallException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
			                     .body(e.getMessage());
		}
		return ResponseEntity.ok("");
	}

	@PatchMapping("/user/image/unvote/{uniqueId}")
	public ResponseEntity<String> unVote(@PathVariable String uniqueId) {
		try {
			imageService.unVote(uniqueId);
		} catch (IllegalCallException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
			                     .body(e.getMessage());
		}
		return ResponseEntity.ok()
		                     .build();
	}

	@GetMapping("/admin/results")
	public ResponseEntity<String> results() {
		return imageService.getAllImagesWithResult();
	}

	@ExceptionHandler(value = MultipartException.class)
	public ResponseEntity<String> handleFileUploadingError(Exception exception) {
		System.err.println("File uploading error: " + exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                     .build();
	}
}
