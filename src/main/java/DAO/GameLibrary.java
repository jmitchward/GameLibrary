package DAO;

import DTO.GENRE;
import DTO.Game;
import DTO.RATING;
import DTO.PLATFORM;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GameLibrary implements GameLibraryInterface{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    Map<String, Game> gameLibrary = new HashMap<>();
    String nextId = "001";
    private final String GAME_LIBRARY;
    public static final String DELIMITER = "::";

    public GameLibrary() throws LibraryNotFound {
        GAME_LIBRARY = "game_library.txt";
        loadFile();
    }

    public GameLibrary(String newFile) throws LibraryNotFound {
        GAME_LIBRARY = newFile;
        loadFile();
    }

    public String generateId() {
        for (int i = 1; i < gameLibrary.size(); i++) {
            if (gameLibrary.get(String.format("%03d", (i))) == null) {
                nextId = String.format("%03d", (i));
            } else {
                nextId = String.format("%03d", (i + 1));
            }
        }
        if (gameLibrary.get(nextId) != null) {
            nextId = String.format("%03d", (Integer.parseInt(nextId) + 1));
        }
        return nextId;
    }

    public Game createGame(Game newGame) {
        Game addedGame = gameLibrary.put(newGame.getGameId(), newGame);
        return addedGame;
    }

    public Game deleteGame(String gameId) {
        Game deletedGame = gameLibrary.remove(gameId);
        return deletedGame;
    }

    public Game getGame(String gameId) {
        return gameLibrary.get(gameId);
    }

    public List<Game> viewLibrary() {
        return new ArrayList<>(gameLibrary.values());
    }

    public int getSize() {
        return gameLibrary.size();
    }

    public void saveTrigger() throws LibraryNotFound {
        saveFile(gameLibrary);
    }

    public void saveFile(Map<String, Game> games) throws LibraryNotFound {
        PrintWriter saver;
        // Declare file writing object.

        try {
            saver = new PrintWriter(new FileWriter(GAME_LIBRARY));
            // Try to implement file writing object.
        } catch (IOException e) {
            throw new LibraryNotFound("Could not load library.");
            // If the file doesn't exist, throw custom exception.
        }

        for (Map.Entry<String, Game> game : games.entrySet()) {
            // For each item in the received map object.
            saver.println(packUp(game.getValue()));
            // Pass item to the marshalling function, formatting it for storage.
        }

        saver.close();
        // Close the saver.
    }

    public void loadFile() throws LibraryNotFound {
        Scanner loader;
        try {
            loader = new Scanner(new BufferedReader(new FileReader(GAME_LIBRARY)));
        } catch (FileNotFoundException e) {
            throw new LibraryNotFound("Could not load library.");
        }
        String thisLine;
        Game nextGame;
        while (loader.hasNextLine()) {
            thisLine = loader.nextLine();
            nextGame = unPack(thisLine);
            gameLibrary.put(nextGame.getGameId(), nextGame);
        }
        loader.close();
    }

    public String packUp(Game game) {
        String gameString = game.getGameId() + DELIMITER;
        gameString += game.getName() + DELIMITER;
        gameString += game.getDatePurchased().format(formatter) + DELIMITER;
        gameString += game.getRating().toString() + DELIMITER;
        gameString += game.getGenre().toString() + DELIMITER;
        gameString += game.getPlatform().toString() + DELIMITER;
        gameString += game.getPlayers() + DELIMITER;
        return gameString;
    }

    public Game unPack(String gameString) {
        String[] gameValues = gameString.split(DELIMITER);
        String gameId = gameValues[0];
        if (Integer.parseInt(gameId) > Integer.parseInt(nextId)) {
            nextId = String.format("%03d", (Integer.parseInt(gameId) + 1));
        } else if (Integer.parseInt(gameId) < Integer.parseInt(nextId)) {

        }
        Game gotGame = new Game(gameId);
        gotGame.setName(gameValues[1]);
        gotGame.setDatePurchased(LocalDate.parse(gameValues[2], formatter));
        gotGame.setRating(RATING.valueOf(gameValues[3]));
        gotGame.setGenre(GENRE.valueOf(gameValues[4]));
        gotGame.setPlatform(PLATFORM.valueOf(gameValues[5]));
        gotGame.setPlayers(Integer.parseInt(gameValues[6]));
        return gotGame;
    }
}
