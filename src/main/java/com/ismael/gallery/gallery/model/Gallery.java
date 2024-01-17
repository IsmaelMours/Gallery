package com.ismael.gallery.gallery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ismael.gallery.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "event_name")
    private String eventName;

    @Lob
    @Column(name = "gallery_img", columnDefinition = "LONGBLOB")
    private byte[] img;

    @Column(name = "img_type")
    private String imgType;

    @Column(name = "img_name")
    private String imgName;

    // Many-to-One relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // Constructors, getters, setters, and other methods remain unchanged
}
