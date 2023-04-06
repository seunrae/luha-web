package com.example.luha.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.luha.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Component
public class CloudiinaryUtils {

        private final CloudinaryConfig config;

        private Map setImageParameter(){
            return ObjectUtils.asMap(
                    "use_filename", false,
                    "public_id", config.getFolderName()+ "/" + UUID.randomUUID(),
                    "unique_filename", false,
                    "overwrite", true
            );
        }


         private boolean imageFileCheck(MultipartFile imagePathDirectory) {
         if (Objects.requireNonNull(imagePathDirectory.getContentType()).contains("image") &&
                imagePathDirectory.getSize() <= 1_000_000)
            return true;

         throw new RuntimeException("An image type file expected or file size is too large");

         }

    private String convertFileToString(MultipartFile multipartFile) throws IOException {
        imageFileCheck(multipartFile);

        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        FileOutputStream fos = new FileOutputStream(convFile);

        fos.write(multipartFile.getBytes());

        fos.close();

        return convFile.getPath();
    }

    private String uploadAndTransformImage(String filePath) {

        try {
            String imageUrl = "";
            Cloudinary cloudinary = new Cloudinary(config.getCloudinaryUrl());

            Map params = setImageParameter();

            imageUrl = cloudinary.uploader().upload(filePath, params).get("url").toString();

            cloudinary.url().transformation(new Transformation()
                            .crop("pad")
                            .width(300)
                            .height(400)
                            .background("auto:predominant"))
                    .imageTag(UUID.randomUUID().toString());

//            File uploadedFile = new File(filePath);
//            uploadedFile.delete();

            return imageUrl;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }

    public String defaultImageUpload() {
        return uploadAndTransformImage(config.getDefaultUrl());
    }
    public String createOrUpdateImage(MultipartFile file) throws IOException {

        String filePath = convertFileToString(file);

        if (filePath.equals(""))
            return defaultImageUpload();

        return uploadAndTransformImage(filePath);
    }



}
