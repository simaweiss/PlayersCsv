package com.assignment.players.service;

import com.assignment.players.exception.PlayerNotFoundException;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CsvFileServiceImpl implements CsvFileService {

    public static final String FILE_NAME = "player.csv";
    public static final String MESSAGE = "player with id: %s not found";

    @Override
    public List<Map<String, String>> getAllPlayers(){

        File file = getFile();
        String[] headerArr = readHeader(file);
        List<String[]> lineList = readContent(file);
        List<String> flattendLineList = flattenListOfArrays(lineList);
        List<Map<String, String>> playerList = buildPlayerList(headerArr, flattendLineList);

        return playerList;
    }

    @Override
    public Map<String, String> getPlayerById(String id) throws PlayerNotFoundException {

        File file = getFile();
        String[] headerArr = readHeader(file);
        List<String[]> linesList = readContent(file);
        List<String> flattendLineList = flattenListOfArrays(linesList);
        List<Map<String, String>> playerList = buildPlayerList(headerArr, flattendLineList);
        for(Map<String,String> player : playerList){
            if(player.containsValue(id)){
                    return player;
            }
        }

        throw new PlayerNotFoundException(String.format(MESSAGE, id));
    }

    private File getFile(){
        try {
            return ResourceUtils.getFile("classpath:".concat(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] readHeader(File file){
        try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
            return csvReader.readNext();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String[]> readContent(File file) {
        try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
            String[] lineValues;
            List<String[]> listOfLines = new ArrayList<>();
            // begin reading body lines after header
            csvReader.readNext();
            while ((lineValues = csvReader.readNext()) != null) {
                listOfLines.add(lineValues);
            }
            return listOfLines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> flattenListOfArrays(List<String[]> listOfArrays){
         return listOfArrays.stream()
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

        private List<Map<String, String>> buildPlayerList(String[] headerArr, List<String> flattenedList) {
        List<Map<String, String>> players = new ArrayList<>();
        Map<String, String> player = new HashMap<>();
        int listLength = flattenedList.size();
        int lastHeaderArrIndex = headerArr.length - 1;
        int indexHeaderArr = 0;

        for (int i = 0; i < listLength; i++) {
            player.put(headerArr[indexHeaderArr], flattenedList.get(i));
            /* Iterating through list of string with values with index i, and through the header array with indexHeaderArr.
               once the index header array reaches its last column for header array, index header will be set back to 0
               and a new player will be created for a new cycle of headers with crosponding values */
            if (indexHeaderArr == lastHeaderArrIndex) {
                players.add(player);
                indexHeaderArr = 0;
                player = new HashMap<>();
            } else {
                indexHeaderArr++;
            }
        }

        return players;
    }
}
