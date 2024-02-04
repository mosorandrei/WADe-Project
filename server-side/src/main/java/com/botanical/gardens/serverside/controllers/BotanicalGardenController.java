package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.entities.BotanicalGarden;
import com.botanical.gardens.serverside.services.BotanicalGardenService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/botanical-gardens")
@RestController
public class BotanicalGardenController {
    private final BotanicalGardenService botanicalGardenService;

    @Autowired
    public BotanicalGardenController(BotanicalGardenService botanicalGardenService) {
        this.botanicalGardenService = botanicalGardenService;
    }

    @GetMapping("/")
    public ResponseEntity<List<JSONObject>> getBotanicalGardens() {
        List<JSONObject> gardens = botanicalGardenService.fetchBotanicalGardens();

        return ResponseEntity.ok(gardens);
    }
}
