package com.tsystems.javaschool.onlinestore.exceptions;

/**
 * Class represents exception thrown when there is an error during image upload
 */
public class ImageUploadException  extends RuntimeException {

    private final String message;
    private final String folder;
    private final Long id;

    public ImageUploadException(String message, String folder, Long id){

        this.message=message;
        this.folder=folder;
        this.id=id;
    }

    @Override
    public String getMessage(){
        return message;
    }

    public String getFolder(){
        return folder;
    }
    public Long getId(){
        return id;
    }

}
