package ServiceLayer;

import DTO.GENRE;
import DTO.RATING;
import DAO.LibraryNotFound;
import DTO.Game;
import DTO.PLATFORM;


import java.time.LocalDate;
import java.util.List;

public interface LibraryServices {
    String getNewId();

    void checkCreateGame(Game newGame) throws EmptyFieldException, DuplicateIdException, LibraryNotFound;

    Game checkDeleteGame(String gameId) throws GameNotFoundException, LibraryNotFound;

    GENRE checkGameGenre(String genre) throws InvalidGenreSelection;

    RATING checkGameRating(int rating) throws InvalidRatingSelection;

    PLATFORM checkGamePlatform(String platform) throws InvalidPlatformException;

    LocalDate checkGameDate(String date) throws InvalidDateException;

    Game getGame(String gameId);

    Game randomGame();

    List<Game> getGames();

    void saveState() throws LibraryNotFound;
}
