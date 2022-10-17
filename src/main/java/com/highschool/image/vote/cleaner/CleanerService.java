package com.highschool.image.vote.cleaner;

import com.highschool.image.vote.image.ImageRepository;
import com.highschool.image.vote.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class CleanerService {

	private final UserRepository userRepository;
	private final ImageRepository imageRepository;


	public void clearDataBase() {
		userRepository.deleteAll();
		imageRepository.deleteAll();
		File directory = new File("src/main/resources/images");
		File[] files = directory.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.getName()
			        .equals(".gitkeep")) {
				continue;
			}
			if (!file.delete()) {
				System.out.println("Failed to delete " + file.getName());
			}
		}
	}
}
