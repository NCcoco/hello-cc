package com.example.mywebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // Handle all requests
                .addResourceLocations("classpath:/static/") // Look in the static folder
                .resourceChain(true) // Enable resource chaining
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        // If the requested resource doesn't exist (e.g., a frontend route like /dictionary)
                        // and it's not an API call (assuming API calls are prefixed, e.g. /api/)
                        // and it's not a file with an extension (like .js, .css, .png)
                        // then serve index.html
                        if (requestedResource.exists() && requestedResource.isReadable()) {
                            return requestedResource;
                        } else if (!resourcePath.startsWith("api/") && resourcePath.matches("^[^\\.]*$")) {
                            // The regex ^[^\\.]*$ matches paths without a file extension.
                            // It means: start of string, any character except dot, zero or more times, end of string.
                            Resource indexHtml = location.createRelative("index.html");
                            if (indexHtml.exists() && indexHtml.isReadable()) {
                                return indexHtml;
                            }
                        }
                        return null; // Let other handlers or Spring Boot default try
                    }
                });
    }
}
