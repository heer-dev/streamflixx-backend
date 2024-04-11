package com.example.streamflixx.service;

import com.example.streamflixx.model.Media;
import com.example.streamflixx.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public List<Media> getAllFeaturedMedia() {
        return mediaRepository.findByIsFeaturedTrue();
    }

}
