package com.ismael.gallery.user.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ismael.gallery.gallery.model.Gallery;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

  @Id
  @GeneratedValue
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  @Column(name = "Contact")
  private String mobile;

  @Lob
  @Column(name = "Profile picture", columnDefinition = "LONGBLOB")
  private byte [] profile;

  // One-to-Many relationship with Gallery
  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Gallery> galleries;

  // Constructors, getters, setters, and other methods remain unchanged

}
