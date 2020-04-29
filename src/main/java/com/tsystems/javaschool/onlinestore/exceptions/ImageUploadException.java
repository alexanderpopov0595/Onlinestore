package com.tsystems.javaschool.onlinestore.exceptions;

public class ImageUploadException  extends RuntimeException {

    private final String message;

    public ImageUploadException(String message){
        this.message=message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
