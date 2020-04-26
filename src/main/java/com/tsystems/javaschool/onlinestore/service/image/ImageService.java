package com.tsystems.javaschool.onlinestore.service.image;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface provides methods to work with images
 */
public interface ImageService {

    /**
     * Method checks image's format
     * @param image
     */
    public void validateImage(MultipartFile image);

    /**
     * Method saves image to server folder
     * @param folder (users, products, etc)
     * @param id (user id, product id, etc)
     * @param image
     */
    public void saveImage(String folder, long id,  MultipartFile image);

    /**
     * Method gets image from form, folder name and file name and adds it to server
     * @param image
     * @param folder
     * @param id
     */
    public void uploadImage(MultipartFile image, String folder, long id);

    /**
     * Method deletes image by name
     * @param folder
     * @param id
     */
    public void deleteImage(String folder, long id);
}
