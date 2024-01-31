package com.botanical.gardens.serverside.repositories;

import com.botanical.gardens.serverside.entities.BotanicalGarden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotanicalGardenRepository extends JpaRepository<BotanicalGarden, Long> {
}
