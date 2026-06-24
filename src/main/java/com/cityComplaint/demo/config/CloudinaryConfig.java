package com.cityComplaint.demo.config;



import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @PostConstruct
    public void check() {
        System.out.println("Cloud Name = " + cloudName);
        System.out.println("API Key = " + apiKey);
    }

    @Value("${CLOUDINARY_CLOUD_NAME}")
    private String cloudName;

    @Value("${CLOUDINARY_API_KEY}")
    private String apiKey;

    @Value("${CLOUDINARY_API_SECRET}")
    private String apiSecret;
//@Value("${CLOUDINARY_CLOUD_NAME:test}")
//private String cloudName;
//
//    @Value("${CLOUDINARY_API_KEY:test}")
//    private String apiKey;
//
//    @Value("${CLOUDINARY_API_SECRET:test}")
//    private String apiSecret;

//    @Bean
//    public Cloudinary cloudinary() {
//
//        return new Cloudinary(
//                ObjectUtils.asMap(
//                        "cloud_name", cloudName,
//                        "api_key", apiKey,
//                        "api_secret", apiSecret,
//                        "secure", true
//                )
//        );
//    }

    @Bean
    public Cloudinary cloudinary() {

        System.out.println("Cloud Name = " + cloudName);
        System.out.println("API Key = " + apiKey);
        System.out.println("API Secret Exists = " + (apiSecret != null));

        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret,
                        "secure", true
                )
        );
    }
}