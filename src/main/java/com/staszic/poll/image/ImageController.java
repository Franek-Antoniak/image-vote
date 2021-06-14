package com.staszic.poll.image;


import com.staszic.poll.error.DataBaseSelectException;
import com.staszic.poll.error.ForbiddenImgUploadException;
import com.staszic.poll.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    @PostMapping("/user/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) throws Exception {
        try {
            userService.addAuthorshipAndCreate();
            imageService.saveImage(imageFile);
        } catch (NullPointerException e) {
            System.out.println("MultipartFile has Null" + e.getMessage());
        } catch (ForbiddenImgUploadException e) {
            System.out.println("Too many image uploads by User" + e.getMessage());
        } catch (DataBaseSelectException e) {
            System.out.println("SELECT exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("No such path/file" + e.getMessage());
        }
        return ResponseEntity.ok("");
    }

    @GetMapping("/admin/results")
    public ResponseEntity<String> results() {
        return imageService.getAllImagesWithResult();
    }

}
