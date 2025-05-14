package com.projects.UrlShortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class UrlShortenerApplication {

    private final UrlMappingRepository urlMappingRepository;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    public UrlShortenerApplication(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

    // ✅ CORS Configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000","url-shortener-frontend-gold.vercel.app","url-shortener-frontend-mvb4jzjhs-samarth-bedares-projects.vercel.app","https://url-shortener-frontend-samarth-bedares-projects.vercel.app") // React frontend origin
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*");
            }
        };
    }

    // Endpoint to shorten the URL
    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> payload) {
        String originalUrl = payload.get("url");
        if (originalUrl == null || originalUrl.isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "URL is required"));
        }

        // Validate URL
        try {
            new URI(originalUrl).toURL();
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Invalid URL format"));
        }

        // Generate unique 6-character code for shortening
        String code = UUID.randomUUID().toString().substring(0, 6);
        while (urlMappingRepository.existsByShortCode(code)) {
            code = UUID.randomUUID().toString().substring(0, 6);
        }

        // Save URL mapping in the database
        UrlMapping mapping = new UrlMapping(code, originalUrl);
        urlMappingRepository.save(mapping);

        // Create and return the short URL
        String shortUrl = baseUrl + "/" + code;
        return ResponseEntity.ok(Map.of("shortUrl", shortUrl));
    }

    // Endpoint to redirect using the shortened URL
    @GetMapping("/{code}")
    public ResponseEntity<Object> redirect(@PathVariable String code) {
        return urlMappingRepository.findByShortCode(code)
                .map(mapping -> ResponseEntity
                        .status(302)
                        .location(URI.create(mapping.originalUrl()))
                        .build())
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .build());
    }
}
