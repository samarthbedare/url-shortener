package com.projects.UrlShortener;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UrlMappingRepository extends MongoRepository<UrlMapping, String> {
    boolean existsByShortCode(String shortCode);
    Optional<UrlMapping> findByShortCode(String shortCode);
}
