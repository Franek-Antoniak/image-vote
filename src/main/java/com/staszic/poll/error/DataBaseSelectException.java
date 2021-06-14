package com.staszic.poll.error;

public class DataBaseSelectException extends Exception{
    public DataBaseSelectException(String errorMessage) {
        super(errorMessage);
    }
}
