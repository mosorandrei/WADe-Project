package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.BotanicalGarden;

import java.util.List;

public interface BotanicalGardenService {
    BotanicalGarden saveBotanicalGarden(BotanicalGarden botanicalGarden);
    BotanicalGarden findBotanicalGardenById(Long botanicalGardenId);
    BotanicalGarden findBotanicalGardenByName(String name);
    List<BotanicalGarden> fetchBotanicalGardens();
}
