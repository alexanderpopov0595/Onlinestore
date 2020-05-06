package com.tsystems.javaschool.onlinestore.service.image;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface provides methods to work with images
 */
public interface ImageService {

    /**
     * Method checks image's format
     * @param image
     * @param folder (users, products, etc)
     * @param id (user id, product id, etc)
     */
  void validateImage(MultipartFile image, String folder, long id);

    /**
     * Method saves image to server folder
     * @param folder (users, products, etc)
     * @param id (user id, product id, etc)
     * @param image
     */
     void saveImage(String folder, long id,  MultipartFile image);

    /**
     * Method gets image from form, folder name and file name and adds it to server
     * @param image
     * @param folder
     * @param id
     */
    void uploadImage(MultipartFile image, String folder, long id);


}
