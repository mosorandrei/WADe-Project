package com.botanical.gardens.serverside.repositories;

import com.botanical.gardens.serverside.entities.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
