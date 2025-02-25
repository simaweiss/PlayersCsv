package com.assignment.players.controller;

import com.assignment.players.exception.PlayerNotFoundException;
import com.assignment.players.service.CsvFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controller")
public class PlayersController {

    @Autowired
    private CsvFileServiceImpl playerRepositoryCsv;

    @GetMapping("/players")
    public ResponseEntity<List<Map<String, String>>> getAllPlayers(){
        List<Map<String, String>> playerList = playerRepositoryCsv.getAllPlayers();
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @GetMapping("/players/{playerID}")
    @ResponseBody
    public ResponseEntity<?> getPlayerByID(@PathVariable(name="playerID") String playerID)  {
        try {
            Map<String, String> player = playerRepositoryCsv.getPlayerById(playerID);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
