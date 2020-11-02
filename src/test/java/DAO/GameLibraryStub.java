package DAO;

import DTO.GENRE;
import DTO.Game;
import DTO.PLATFORM;
import DTO.RATING;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameLibraryStub implements GameLibraryInterface{

    public Game onlyGame;
    List<Game> gameLibrary;

    public GameLibraryStub() {
        onlyGame = new Game("002");
        onlyGame.setName("Civilization");
        onlyGame.setPlayers(1);
        onlyGame.setPlatform(PLATFORM.PC);
        onlyGame.setGenre(GENRE.SIMULATION);
        onlyGame.setRating(RATING.FIVE);
        onlyGame.setDatePurchased(LocalDate.now());
    }

    public GameLibraryStub(Game onlyGame) {
        this.onlyGame = onlyGame;
    }

    @Override
    public String generateId() {
        return "001";
    }

    @Override
    public Game createGame(Game newGame) {
        if (newGame.getGameId().equals(onlyGame.getGameId())) {
            return onlyGame;
        }
        else {
            return null;
        }
    }

    @Override
    public Game deleteGame(String gameId) {
        if (onlyGame.getGameId().equals(gameId)) {
            return onlyGame;
        }
        else {
            return null;
        }
    }

    @Override
    public Game getGame(String gameId) {
        if (onlyGame.getGameId().equals(gameId)) {
            return onlyGame;
        }
        else {
            return null;
        }
    }

    @Override
    public List<Game> viewLibrary() {
        gameLibrary = new ArrayList<>();
        return gameLibrary;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public void saveTrigger() throws LibraryNotFound {
        //
    }

    @Override
    public void saveFile(Map<String, Game> games) throws LibraryNotFound {
        //
    }

    @Override
    public void loadFile() throws LibraryNotFound {
        //
    }

    @Override
    public String packUp(Game game) {
        return null;
    }

    @Override
    public Game unPack(String gameString) {
        return null;
    }
}
