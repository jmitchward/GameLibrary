package Controller;

import DAO.LibraryNotFound;
import DTO.Game;
import View.LibraryView;
import ServiceLayer.*;

public class LibraryController {

    LibraryServices service;
    LibraryView view;

    public LibraryController(LibraryServices service, LibraryView view) {
        this.service = service;
        this.view = view;
    }

    public void runLibrary() throws InvalidDateException, InvalidGenreSelection, EmptyFieldException, InvalidRatingSelection, DuplicateIdException, InvalidPlatformException, GameNotFoundException, LibraryNotFound {
        String selection = view.mainMenu();
        switch (selection) {
            case "1":
                addGame();
                runLibrary();
            case "2":
                deleteGame();
                runLibrary();
            case "3":
                viewGame();
                runLibrary();
            case "4":
                randomGame();
                runLibrary();
            case "5":
                viewAllGames();
                runLibrary();
            case "6":
                exit();
            default:
                //view.userError();
                runLibrary();
        }
    }

    public void addGame() throws InvalidDateException, InvalidPlatformException, InvalidGenreSelection, InvalidRatingSelection, EmptyFieldException, DuplicateIdException, LibraryNotFound {
        boolean errorState = false;
        Game newGame = new Game(service.getNewId());

        newGame.setName(view.getName());

        do {
            String purchaseDate = view.selectPurchaseDate();
            try {
                newGame.setDatePurchased(service.checkGameDate(purchaseDate));
                errorState = false;
            } catch (InvalidDateException e) {
                errorState = true;
            }
        } while (errorState);

        do {
            String platform = view.getPlatform();
            try {
                newGame.setPlatform(service.checkGamePlatform(platform));
                errorState = false;
            } catch (InvalidPlatformException e) {
                errorState = true;
            }
        } while (errorState);

        newGame.setPlayers(view.getPlayers());

        do {
            String genre = view.selectGenre();
            try {
                newGame.setGenre(service.checkGameGenre(genre));
                errorState = false;
            } catch (InvalidGenreSelection e) {
                errorState = true;
            }
        } while (errorState);

        do {
            int rating = view.selectRating();
            try {
                newGame.setRating(service.checkGameRating(rating));
                errorState = false;
            } catch (InvalidRatingSelection e) {
                errorState = true;
            }
        } while (errorState);

        service.checkCreateGame(newGame);
        view.displaySuccess(newGame.getName(), "added.");
    }

    public void deleteGame() throws GameNotFoundException, LibraryNotFound {
        Game deletedGame = service.checkDeleteGame(view.getGameId());
        view.displaySuccess(deletedGame.getName(), "deleted.");
    }

    public void viewGame() {
        Game retrievedGame = service.getGame(view.getGameId());
        view.displayGame(retrievedGame);
    }

    public void randomGame() {
        Game randomGame = service.randomGame();
        view.displayGame(randomGame);
    }

    public void viewAllGames() {
        view.displayGame(service.getGames());
    }

    public void exit() throws LibraryNotFound {
        service.saveState();
        view.displayExit();
        System.exit(0);
    }


}
