package com.botanical.gardens.serverside.repositories.jpa;

import com.botanical.gardens.serverside.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
