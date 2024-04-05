package com.example.streamflixx.controller;

import com.example.streamflixx.model.Media;
import com.example.streamflixx.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private final MediaRepository mediaRepository;


    @Autowired
    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMedia(@RequestBody Media media) {
        if (!isValidMedia(media)) {
            return new ResponseEntity<>("Invalid or incomplete media data", HttpStatus.BAD_REQUEST);
        }

        Media savedMedia = mediaRepository.save(media);
        return new ResponseEntity<>(savedMedia, HttpStatus.CREATED);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Media>> getAllMovies() {
        List<Media> movies = mediaRepository.findByIsMovie(true);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/tvshows")
    public ResponseEntity<List<Media>> getAllTvShows() {
        List<Media> tvShows = mediaRepository.findByIsMovie(false);
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Media>> searchByTitle(@RequestParam String title) {
        List<Media> results = mediaRepository.findByNameContainingIgnoreCase(title);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Media>> getFeaturedMedia(@RequestParam(required = false) String type) {
        List<Media> featuredMedia;

        if (type != null) {
            boolean isMovie = "movies".equalsIgnoreCase(type);
            boolean isTvShow = "tvshows".equalsIgnoreCase(type);

            if (isMovie || isTvShow) {
                featuredMedia = mediaRepository.findByIsFeaturedAndIsMovie(true, isMovie);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            featuredMedia = mediaRepository.findByIsFeaturedTrue();
        }

        return new ResponseEntity<>(featuredMedia, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMediaById(@PathVariable String id)
    {
        // Validate the ID format if needed, for example, checking if it's a valid MongoDB ObjectId
        if (!isValidId(id)) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }
        Optional<Media> media = mediaRepository.findById(id);
        if (media.isPresent()) {
            return ResponseEntity.ok(media.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedia(@PathVariable String id, @RequestBody Media updatedMedia) {
        if (!isValidId(id)) {
            return new ResponseEntity<>("Invalid movie ID format", HttpStatus.BAD_REQUEST);
        }

        Optional<Media> existingMediaOptional = mediaRepository.findById(id);
        if (existingMediaOptional.isEmpty()) {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }

        Media existingMedia = existingMediaOptional.get();

        if (updatedMedia == null) {
            return new ResponseEntity<>("Incoming data is missing", HttpStatus.BAD_REQUEST);
        }

        Double updatedRentPrice = updatedMedia.getRentPrice();
        if (updatedRentPrice != null) {
            existingMedia.setRentPrice(updatedRentPrice);
        }

        Media savedMedia = mediaRepository.save(existingMedia);

        return new ResponseEntity<>(savedMedia, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedia(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return new ResponseEntity<>("Invalid ID provided", HttpStatus.BAD_REQUEST);
        }

        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if (optionalMedia.isEmpty()) {
            return new ResponseEntity<>("Movie or TV show not found", HttpStatus.NOT_FOUND);
        }

        mediaRepository.deleteById(id);
        return new ResponseEntity<>("Movie or TV show deleted successfully", HttpStatus.OK);
    }

    private boolean isValidMedia(Media media) {
        return media != null && media.getName() != null && !media.getName().isEmpty() &&
                media.getPrice() != null && media.getSynopsis() != null && media.getIsMovie() != null &&
                media.getSmallPosterPath() != null && media.getLargePosterPath() != null &&
                media.getRentPrice() != null && media.getPurchasePrice() != null && media.getPrice() != null &&
                media.getIsFeatured() != null;
    }

    private boolean isValidId(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return true;
    }
}
