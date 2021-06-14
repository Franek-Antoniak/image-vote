package com.staszic.poll.error;

public class ForbiddenImgUploadException extends Exception {
    public ForbiddenImgUploadException(String errorMessage) {
        super(errorMessage);
    }
}
