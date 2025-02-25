package com.assignment.players.service;

import com.assignment.players.exception.PlayerNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface CsvFileService {
    List<Map<String, String>> getAllPlayers() throws FileNotFoundException;
    Map<String, String> getPlayerById(String playerId) throws PlayerNotFoundException;
}
