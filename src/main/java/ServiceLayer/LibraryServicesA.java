package ServiceLayer;

import DTO.GENRE;
import DTO.RATING;
import DAO.libraryEventLog;
import DAO.GameLibrary;
import DAO.LibraryNotFound;
import DTO.Game;
import DTO.PLATFORM;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class LibraryServicesA implements LibraryServices {

    GameLibrary dao;
    libraryEventLog logger;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public LibraryServicesA(GameLibrary dao, libraryEventLog logger) {
        this.dao = dao;
        this.logger = logger;
    }

    @Override
    public String getNewId() {
        return dao.generateId();
    }

    @Override
    public void checkCreateGame(Game newGame) throws EmptyFieldException, DuplicateIdException, LibraryNotFound {
        // Check that each field is properly filled in
        if (newGame.getName() == null
                || newGame.getPlatform() == null
                || newGame.getGenre() == null
                || newGame.getRating() == null
                || newGame.getDatePurchased() == null
                || newGame.getPlayers() == 0) {
            throw new EmptyFieldException("New game has empty fields.");
        } else if (dao.getGame(newGame.getGameId()) != null) {
            throw new DuplicateIdException("Game ID already exists.");
        } else {
            dao.createGame(newGame);
            logger.writeLogEntry(newGame.getGameId() + " | " + newGame.getName() + " added.");
        }
    }

    @Override
    public Game checkDeleteGame(String gameId) throws GameNotFoundException, LibraryNotFound {
        if (dao.getGame(gameId) == null) {
            throw new GameNotFoundException("Unable to delete game.");
        } else {
            Game deletedGame = dao.deleteGame(gameId);
            logger.writeLogEntry(deletedGame.getGameId() + " | " + deletedGame.getName() + " removed.");
            return deletedGame;
        }
    }

    @Override
    public GENRE checkGameGenre(String genre) throws InvalidGenreSelection {
        switch (genre) {
            case "1":
            case "rpg":
                return GENRE.RPG;
            case "2":
            case "strategy":
                return GENRE.STRATEGY;
            case "3":
            case "adventure":
                return GENRE.ADVENTURE;
            case "4":
            case "action":
                return GENRE.ACTION;
            case "5":
            case "simulation":
                return GENRE.SIMULATION;
            case "6":
            case "racing":
                return GENRE.RACING;
            default:
                throw new InvalidGenreSelection("Invalid Genre Selection");
        }
    }

    @Override
    public RATING checkGameRating(int rating) throws InvalidRatingSelection {
        switch (rating) {
            case 1:
                return RATING.ONE;
            case 2:
                return RATING.TWO;
            case 3:
                return RATING.THREE;
            case 4:
                return RATING.FOUR;
            case 5:
                return RATING.FIVE;
            default:
                throw new InvalidRatingSelection("Invalid Rating Selection");

        }
    }

    @Override
    public PLATFORM checkGamePlatform(String platform) throws InvalidPlatformException {
        switch (platform) {
            case "1":
            case "playstation":
                return PLATFORM.Playstation;
            case "2":
            case "xbox":
                return PLATFORM.Xbox;
            case "3":
            case "pc":
                return PLATFORM.PC;
            case "4":
            case "switch":
                return PLATFORM.Switch;
            default:
                throw new InvalidPlatformException("Invalid Platform Selection");
        }
    }

    @Override
    public LocalDate checkGameDate(String date) throws InvalidDateException {
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new InvalidDateException("Invalid Date Entry");
        }
    }

    @Override
    public Game getGame(String gameId) {
        return dao.getGame(gameId);
    }

    @Override
    public Game randomGame() {
        Random r = new Random();
        int randomId = (r.nextInt(dao.getSize()));
        return dao.getGame(String.format("%03d", (randomId)));
    }

    @Override
    public List<Game> getGames() {
        return dao.viewLibrary();
    }

    @Override
    public void saveState() throws LibraryNotFound {
        dao.saveTrigger();
    }
}
