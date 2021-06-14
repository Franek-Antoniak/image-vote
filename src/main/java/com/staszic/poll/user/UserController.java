package com.staszic.poll.user;

import com.staszic.poll.error.DataBaseSelectException;
import com.staszic.poll.freemarker.FreeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final FreeMarkerService freeMarkerService;
    private final UserService userService;
    private final CurrentUserService currentUserService;

    @GetMapping("/")
    public ResponseEntity<String> homepage() throws Exception {
        try {
            return userService.getHomePage();
        } catch (DataBaseSelectException e) {
            e.printStackTrace();
            return freeMarkerService.getResponseEntityHTML("error-403.ftl", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/creator")
    public ResponseEntity<String> creator() throws Exception {
        try {
            return userService.getCreatorIfUserCanUpload();
        } catch (DataBaseSelectException e) {
            e.printStackTrace();
            return freeMarkerService.getResponseEntityHTML("error-403.ftl", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/user/vote/{author}")
    public ResponseEntity<String> vote(@PathVariable String author) throws Exception {
        try {
            userService.voteByUser(author);
        } catch (AccessDeniedException e) {
            return ResponseEntity.
                    status(HttpStatus.FORBIDDEN)
                    .body("Too many votes!");
        } catch (DataBaseSelectException e) {
            System.out.println("SELECT exception: " + e.getMessage());
        }
        return ResponseEntity.ok("");
    }

}
