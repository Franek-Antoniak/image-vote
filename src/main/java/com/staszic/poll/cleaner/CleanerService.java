package com.staszic.poll.cleaner;

import com.staszic.poll.image.ImageRepository;
import com.staszic.poll.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CleanerService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;


    public void clearDataBase() {
        userRepository.deleteAll();
        imageRepository.deleteAll();
    }
}
