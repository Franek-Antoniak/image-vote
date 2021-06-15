package com.staszic.poll.user;


import com.staszic.poll.error.DataBaseSelectException;
import com.staszic.poll.error.ForbiddenImgUploadException;
import com.staszic.poll.freemarker.FreeMarkerService;
import com.staszic.poll.image.Image;
import com.staszic.poll.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final Supplier<Exception> DBExceptionSup = () -> new DataBaseSelectException("After all operations there is null in Select");
    private final ImageRepository imageRepository;
    private final FreeMarkerService freeMarkerService;

    public void createUser() {
        User user = new User();
        String name = currentUserService.getName();
        user.setName(name);
        userRepository.save(user);
    }

    public User getIfUserExistsOrCreate() throws Exception {
        Optional<User> optionalUser = userRepository.findByName(currentUserService.getName());
        if (optionalUser.isEmpty()) {
            createUser();
            optionalUser = userRepository.findByName(currentUserService.getName());
        }
        return optionalUser.orElseThrow(DBExceptionSup);
    }

    public void voteByUser(String author) throws Exception {
        Image realImage = getIfImageExists(author);
        User user = getIfUserExistsOrCreate();
        if (!user.vote()) {
            throw new AccessDeniedException("Too many votes!");
        }
        if(user.getHashSet().contains(realImage.getUniqueId()))
            return;
        realImage.vote();
        user.getHashSet().add(realImage.getUniqueId());
        userRepository.save(user);
        imageRepository.save(realImage);
    }

    public void addAuthorshipAndCreate() throws Exception {
        User user = getIfUserExistsOrCreate();
        if(user.isCastVote())
            throw new ForbiddenImgUploadException("Too many votes");
        user.setCastVote(true);
        userRepository.save(user);
    }

    public Image getIfImageExists(String author) throws Exception {
        Optional<Image> optionalImage = imageRepository.findByAuthor(author);
        return optionalImage.orElseThrow(DBExceptionSup);
    }

    public ResponseEntity<String> getCreatorIfUserCanUpload() throws Exception {
        User user = getIfUserExistsOrCreate();
        if(user.isCastVote())
            return freeMarkerService.getResponseEntityHTML("error-403.ftl", HttpStatus.FORBIDDEN);
        return freeMarkerService.getResponseEntityHTML("creator.ftl");
    }

    public ResponseEntity<String> getHomePage() throws Exception {
        User user = getIfUserExistsOrCreate();
        List<Image> imageList = imageRepository.findAll();
        if(user.getVotes() == 0)
            return freeMarkerService.getResponseEntityHTML("error-403.ftl", HttpStatus.FORBIDDEN);
        return freeMarkerService.getResponseEntityHTML(
                "homepage.ftl",
                new String[]{"user", "imageList"},
                new Object[]{user, imageList});
    }

    public ResponseEntity<String> getSettings() {
        return freeMarkerService.getResponseEntityHTML("settings.ftl");
    }
}
