package com.botanical.gardens.serverside.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "tour_id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String guideName;
    private int totalSeats;
    private LocalDateTime startHour;
    private LocalDateTime endHour;

    @ManyToMany
    @JoinTable(
            name = "tours_attractions",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
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