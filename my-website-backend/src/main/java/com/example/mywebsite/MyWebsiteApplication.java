package com.example.mywebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@SpringBootApplication
public class MyWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWebsiteApplication.class, args);
    }

    // Global CORS configuration bean
    // This allows requests from any origin, with common methods and headers.
    // For production, you might want to restrict origins.
    // This is useful for local development with Vue CLI server (e.g., http://localhost:8081).
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow cookies, authorization headers, etc.
        // config.addAllowedOrigin("*"); // Allow all origins - for dev. For prod, list specific origins.
        config.addAllowedOriginPattern("*"); // More flexible pattern for origins, e.g. http://localhost:*, https://*.yourdomain.com
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config); // Apply this configuration to all paths
        return new CorsFilter(source);
    }
}
