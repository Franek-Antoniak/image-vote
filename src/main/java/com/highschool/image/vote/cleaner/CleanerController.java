package com.highschool.image.vote.cleaner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CleanerController {
	private final CleanerService cleanerService;

	@PatchMapping("/admin/delete/data")
	public ResponseEntity<String> deleteAllData() {
		cleanerService.clearDataBase();
		return ResponseEntity.status(HttpStatus.OK)
		                     .build();
	}
}
