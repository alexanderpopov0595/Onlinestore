package com.tsystems.javaschool.onlinestore.service.image;

import com.tsystems.javaschool.onlinestore.exceptions.ImageUploadException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger= Logger.getLogger(ImageServiceImpl.class);

    /**
     * Method checks is image format is image/jpeg
     * If not - method throws exception
     * @param image
     */
    @Override
    public void validateImage(MultipartFile image, String folder , long id) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new ImageUploadException("Only JPG images accepted", folder ,id);
        }
    }

    /**
     * Method creates file path by folder and image id and writes image to specific folder
     * @param folder (users, products, etc)
     * @param id (user id, product id, etc)
     * @param image
     */
    @Override
    public void saveImage(String folder, long id,  MultipartFile image) {
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "images" + File.separator+folder+File.separator);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try( BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(dir + File.separator + id+".jpg"))) {
            stream.write(image.getBytes());
        } catch (IOException e) {
            logger.error("Unable to save image: "+ e.getStackTrace());
            throw new ImageUploadException("Unable to save image", folder, id);
        }
    }

    /**
     * Method validates is image empty and saves image
     * @param image
     * @param folder
     * @param id
     */
    public void uploadImage(MultipartFile image, String folder, long id) {
        if (!image.isEmpty()) {
            validateImage(image, folder ,id);
            saveImage(folder, id, image);
        }
    }



}