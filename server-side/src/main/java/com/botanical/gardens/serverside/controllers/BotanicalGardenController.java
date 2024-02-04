package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.entities.BotanicalGarden;
import com.botanical.gardens.serverside.services.BotanicalGardenService;
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
    public ResponseEntity<List<BotanicalGarden>> getBotanicalGardens() {
        List<BotanicalGarden> gardens = botanicalGardenService.fetchBotanicalGardens();

        if (!gardens.isEmpty()) {
            return ResponseEntity.ok(gardens);
        } else {
            // build one garden if no content TODO
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getBotanicalGardenNames() {
        List<String> gardenNames = botanicalGardenService.fetchBotanicalGardens().stream().map(BotanicalGarden::getName).toList();
        if (!gardenNames.isEmpty()) {
            return ResponseEntity.ok(gardenNames);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getBotanicalGardenByName(@PathVariable String name) {
        try {
            BotanicalGarden garden = botanicalGardenService.findBotanicalGardenByName(name);
            return ResponseEntity.ok(garden);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}
