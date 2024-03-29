package com.botanical.gardens.serverside.repositories.jpa;

import com.botanical.gardens.serverside.entities.BotanicalGarden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BotanicalGardenRepository extends JpaRepository<BotanicalGarden, Long> {
}
