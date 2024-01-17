package com.ismael.gallery.gallery.controller;


import com.ismael.gallery.exceptions.UserNotFoundException;
import com.ismael.gallery.gallery.model.Gallery;
import com.ismael.gallery.gallery.repository.GalleryRepository;
import com.ismael.gallery.gallery.service.GalleryService;
import com.ismael.gallery.user.model.User;
import com.ismael.gallery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private GalleryRepository galleryRepository;

    @PostMapping("/upload")
    public ResponseEntity<Gallery> uploadImg(@RequestPart("event") String event, @RequestParam("file") MultipartFile file) {
        try {
            Gallery gallery = new Gallery();
            gallery.setEventName(event);
            gallery.setImgName(file.getOriginalFilename());
            gallery.setImg(file.getBytes());
            gallery.setImgType(file.getContentType());
            galleryRepository.save(gallery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/image/{Id}")
    public ResponseEntity<Gallery> getImage(@PathVariable Long Id) {
        Gallery gallery = galleryService.getFile(Id);
        return ResponseEntity.ok(gallery);
    }

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @PostMapping("/multiple")
    public ResponseEntity<List<Gallery>> ploadImages(
            @RequestParam("userId") Long userId, // Include user ID in the request
            @RequestPart("event") String event,
            @RequestParam("file") MultipartFile[] files
    ) {
        try {
            // Retrieve the user from the database
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

            List<Gallery> savedImgs = galleryService.saveImages(user, event, files);
            return ResponseEntity.ok(savedImgs);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Gallery>> getAllImages() {
        List<Gallery> galleries = galleryRepository.findAll();
        return ResponseEntity.ok(galleries);
    }


}
