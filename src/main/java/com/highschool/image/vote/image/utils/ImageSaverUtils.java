package com.highschool.image.vote.image.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ImageSaverUtils {
	private final ImageCompressorUtils imageCompressor;

	/*
	 * @param imageFile - image file to save
	 * @return - new file name
	 */
	public String saveImage(MultipartFile image) throws IOException {
		Path folderPath = Paths.get("images");
		createFolderIfNotExists(folderPath);
		String newFileName = getNewRandomFileName().concat(".jpg");
		Path filePath = Paths.get(folderPath + "/" + newFileName);
		imageCompressor.compressImage(filePath, image);
		return newFileName;
	}

	public void createFolderIfNotExists(Path folderPath) throws IOException {
		if (!Files.exists(folderPath)) {
			Files.createDirectory(folderPath);
		}
	}

	public String getNewRandomFileName() {
		int leftLimit = 'a';
		int rightLimit = 'z';
		int targetStringLength = 20;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1)
		             .limit(targetStringLength)
		             .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		             .toString();
	}
}
