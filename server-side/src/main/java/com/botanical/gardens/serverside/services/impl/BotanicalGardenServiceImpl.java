package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.BotanicalGarden;
import com.botanical.gardens.serverside.repositories.jpa.BotanicalGardenRepository;
import com.botanical.gardens.serverside.repositories.owl.BotanicalGardenOwlRepository;
import com.botanical.gardens.serverside.services.BotanicalGardenService;
import lombok.Getter;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BotanicalGardenServiceImpl implements BotanicalGardenService {
    @Getter
    private final BotanicalGardenRepository botanicalGardenRepository;
    private final BotanicalGardenOwlRepository botanicalGardenOwlRepository;

    @Autowired
    public BotanicalGardenServiceImpl(BotanicalGardenRepository botanicalGardenRepository, BotanicalGardenOwlRepository botanicalGardenOwlRepository) {
        this.botanicalGardenRepository = botanicalGardenRepository;
        this.botanicalGardenOwlRepository = botanicalGardenOwlRepository;
    }

    @Override
    public void saveBotanicalGarden(BotanicalGarden botanicalGarden) {
        botanicalGardenRepository.save(botanicalGarden);
    }

    @Override
    public List<JSONObject> fetchBotanicalGardens() {
        return botanicalGardenOwlRepository.getAllGardens();
    }
}
