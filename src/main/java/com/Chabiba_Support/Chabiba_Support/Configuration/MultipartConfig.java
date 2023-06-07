package com.Chabiba_Support.Chabiba_Support.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfig {

    public StandardServletMultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
//        resolver.setMaxUploadSize(10485760); // Set maximum upload size
        return resolver;
    }
}