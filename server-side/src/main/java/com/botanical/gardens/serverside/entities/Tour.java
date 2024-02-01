package com.botanical.gardens.serverside.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String guideName;
    private int totalSeats;
    private Date startHour;
    private Date endHour;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Attraction> attractions;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<User> participants;


}