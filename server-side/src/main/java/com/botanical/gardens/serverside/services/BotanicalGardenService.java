package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.BotanicalGarden;
import net.minidev.json.JSONObject;

import java.util.List;

public interface BotanicalGardenService {
    void saveBotanicalGarden(BotanicalGarden botanicalGarden);

    List<JSONObject> fetchBotanicalGardens();
}
