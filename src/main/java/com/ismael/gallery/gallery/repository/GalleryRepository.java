package com.ismael.gallery.gallery.repository;



import com.ismael.gallery.gallery.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {


}