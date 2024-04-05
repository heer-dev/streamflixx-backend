package com.example.streamflixx.repository;

import com.example.streamflixx.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface MediaRepository extends MongoRepository<Media, String>
{
    List<Media> findByIsMovie(Boolean isMovie);
    List<Media> findByNameContainingIgnoreCase(String name);
    List<Media> findByIsFeaturedTrue();
    List<Media> findByIsFeaturedAndIsMovie(Boolean isFeatured, Boolean isMovie);
}
