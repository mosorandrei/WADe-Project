package com.botanical.gardens.serverside.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "attractions")
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attraction_id", nullable = false)
    private Long id;
    private String name;
    private String type;
    private String description;
    @Lob
    private byte[] photo;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Review> reviews;

    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attraction that = (Attraction) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}