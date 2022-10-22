package com.picscore.cleaner;

import com.picscore.image.repository.ImageRepository;
import com.picscore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class CleanerService {

	private final UserRepository userRepository;
	private final ImageRepository imageRepository;


	public void clearDataBase() {
		File[] files = Paths.get("images")
		                    .toFile()
		                    .listFiles();
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
