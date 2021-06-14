package com.staszic.poll.image;

import com.staszic.poll.freemarker.FreeMarkerService;
import com.staszic.poll.user.CurrentUserService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final String folderPath = "images/";
    private final CurrentUserService currentUserService;
    private final ImageRepository imageRepository;
    private final FreeMarkerService freeMarkerService;

    public void saveImage(@NotNull MultipartFile imageFile) throws NullPointerException, IOException {
        byte[] imageBytes = imageFile.getBytes();
        String ending = ".".concat(imageFile.getOriginalFilename().split("\\.")[1]);
        String randomEnd = randomString().concat(ending);
        Path path = Paths.get(folderPath + randomEnd);
        Files.write(path, imageBytes);
        Image image = new Image();
        image.setAuthor(currentUserService.getName());
        image.setPath("/" + folderPath + randomEnd);
        imageRepository.save(image);
    }

    public String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public ResponseEntity<String> getAllImagesWithResult() {
        List<Image> imageList = imageRepository.findAllByOrderByVotesDesc();
        return freeMarkerService.getResponseEntityHTML("results.ftl", "imageList", imageList);
    }
}
