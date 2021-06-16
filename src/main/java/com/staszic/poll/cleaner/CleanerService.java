package com.staszic.poll.cleaner;

import com.staszic.poll.image.ImageRepository;
import com.staszic.poll.user.UserRepository;
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
        File directory = new File("images");

        File[] files = directory.listFiles();
        assert files != null;
        for (File file: files) {
            if(!file.delete())
                System.out.println("Failed to delete " + file.getName());
        }
        System.out.println("Files from: " + directory.getName() + "deleted");
    }
}
