package com.botanical.gardens.serverside.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "botanical_gardens")
public class BotanicalGarden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tour> tours;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attraction> attractions;
}