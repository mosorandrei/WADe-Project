package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.BotanicalGarden;
import com.botanical.gardens.serverside.repositories.BotanicalGardenRepository;
import com.botanical.gardens.serverside.services.BotanicalGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BotanicalGardenServiceImpl implements BotanicalGardenService {

    private final BotanicalGardenRepository botanicalGardenRepository;

    @Autowired
    public BotanicalGardenServiceImpl(BotanicalGardenRepository botanicalGardenRepository) {
        this.botanicalGardenRepository = botanicalGardenRepository;
    }

    @Override
    public BotanicalGarden saveBotanicalGarden(BotanicalGarden botanicalGarden) {
        return botanicalGardenRepository.save(botanicalGarden);
    }

    @Override
    public BotanicalGarden findBotanicalGardenById(Long botanicalGardenId) {
        return botanicalGardenRepository.findById(botanicalGardenId).orElseThrow(() -> new RuntimeException(String.format("Could not find Botanical Garden with the id: %s", botanicalGardenId)));
    }

    @Override
    public BotanicalGarden findBotanicalGardenByName(String name) {
        return  botanicalGardenRepository.findByName(name).orElseThrow(() -> new RuntimeException(String.format("Could not find Botanical Garden with the name: %s", name)));
    }

    @Override
    public List<BotanicalGarden> fetchBotanicalGardens() {
        return botanicalGardenRepository.findAll();
    }

    public BotanicalGardenRepository getBotanicalGardenRepository() {
        return botanicalGardenRepository;
    }
}
