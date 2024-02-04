package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.services.BotanicalGardenService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/botanical-gardens")
@CrossOrigin(origins = {"http://localhost:5173", "https://botanical-garden-guide.netlify.app"})
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
