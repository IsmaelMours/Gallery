package com.ismael.gallery.gallery.service;

import com.ismael.gallery.gallery.model.Gallery;
import com.ismael.gallery.gallery.repository.GalleryRepository;

import com.ismael.gallery.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    public Gallery getFile(Long Id) {
        return galleryRepository.findById(Id).orElseThrow();
    }
    public List<Gallery> saveImages(User user, String event, MultipartFile[] images) throws IOException {
        List<Gallery> savedImages = new ArrayList<>();
        for (MultipartFile img : images) {
            String fileName = StringUtils.cleanPath(img.getOriginalFilename());
            Gallery gallery = new Gallery();
            gallery.setImgName(fileName);
            gallery.setImgType(img.getContentType());
            gallery.setImg(img.getBytes());
            gallery.setEventName(event);
            gallery.setUser(user); // Set the user for the gallery
            savedImages.add(galleryRepository.save(gallery));
        }
        return savedImages;
    }

    public List<Gallery> getAllImgs() {
        return galleryRepository.findAll();
    }


}
