package com.example.luha.cloudinary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
public class CloudinaryConfig {
    @Value("${cloudinary.cloud_name}")
    private  String cloudName;
    @Value("${cloudinary.api_key}")
    private  String apiKey;
    @Value("${cloudinary.api_secret}")
    private  String apiSecret;
    @Value("${cloudinary.folder_name}")
    private  String folderName;
    @Value("${cloudinary.cloudinary_url}")
    private  String cloudinaryUrl;
    @Value("${cloudinary.default_avatar}")
    private  String defaultUrl;
}
