package com.picscore.image.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.Iterator;

@Component
public class ImageCompressorUtils {
	private final float imageQuality = 0.3f;

	public void compressImage(Path newPath, MultipartFile multipartFile) throws IOException {
		InputStream inputStream = multipartFile.getInputStream();
		Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");
		if (!imageWriters.hasNext()) {
			throw new IllegalStateException("Writers Not Found!!");
		}
		ImageWriter imageWriter = imageWriters.next();
		File compressedImageFile = newPath.toFile();
		OutputStream outputStream = new FileOutputStream(compressedImageFile);
		ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
		imageWriter.setOutput(imageOutputStream);
		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriteParam.setCompressionQuality(imageQuality);
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
		inputStream.close();
		outputStream.close();
	}
}