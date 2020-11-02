package ServiceLayer;

import DAO.LibraryNotFound;
import DTO.GENRE;
import DTO.Game;
import DTO.PLATFORM;
import DTO.RATING;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServicesATest {

    private LibraryServices service;

    public LibraryServicesATest () {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean("service", LibraryServices.class);
    }

    @Test
    public void testCheckCreateGame() {
        Game newGame = new Game(service.getNewId());
        newGame.setName("Civilization");
        newGame.setPlayers(1);
        newGame.setPlatform(PLATFORM.PC);
        newGame.setGenre(GENRE.SIMULATION);
        newGame.setRating(RATING.FIVE);
        newGame.setDatePurchased(LocalDate.now());
        try {
            service.checkCreateGame(newGame);
        } catch (EmptyFieldException | DuplicateIdException | LibraryNotFound e) {
            fail("Error generated.");
        }
    }

    @Test
    public void testDeleteGame() throws Exception, LibraryNotFound, GameNotFoundException {
        // Arrange
        Game newGameTwo = new Game("001");
        newGameTwo.setName("TestTwo");
        newGameTwo.setPlayers(2);
        newGameTwo.setPlatform(PLATFORM.Playstation);
        newGameTwo.setGenre(GENRE.ACTION);
        newGameTwo.setRating(RATING.FIVE);
        newGameTwo.setDatePurchased(LocalDate.now());

        // Act & Assert
        Game shouldBeMe = service.checkDeleteGame("001");
        assertNotNull(shouldBeMe, "Removing 001 should be not null.");
        assertEquals(newGameTwo.getGameId(), shouldBeMe.getGameId(), "Student removed from 001 should be me.");
    }

    @Test
    public void testGameGenre() throws InvalidGenreSelection {
        // Assign & Act
        GENRE returnedGenre = service.checkGameGenre("1");
        // Assert
        assertEquals(GENRE.RPG, returnedGenre);

        // Assign & Act
        returnedGenre = service.checkGameGenre("rpg");
        // Assert
        assertEquals(GENRE.RPG, returnedGenre);

        // Assign & Act
        returnedGenre = service.checkGameGenre("2");
        assertEquals(GENRE.STRATEGY, returnedGenre);

        // Assign & Act
        returnedGenre = service.checkGameGenre("adventure");
        assertEquals(GENRE.ADVENTURE, returnedGenre);
    }

    @Test
    public void testGameRating() throws InvalidGenreSelection, InvalidRatingSelection {
        // Assign & Act
        RATING returnedRating = service.checkGameRating(1);
        // Assert
        assertEquals(RATING.ONE, returnedRating);

        // Assign & Act
        returnedRating = service.checkGameRating(3);
        // Assert
        assertEquals(RATING.THREE, returnedRating);

        // Assign & Act
        returnedRating = service.checkGameRating(2);
        assertEquals(RATING.TWO, returnedRating);
    }

    @Test
    public void testGamePlatform() throws InvalidGenreSelection, InvalidRatingSelection, InvalidPlatformException {
        // Assign & Act
        PLATFORM returnedPlatform = service.checkGamePlatform("1");
        // Assert
        assertEquals(PLATFORM.Playstation, returnedPlatform);

        // Assign & Act
        returnedPlatform = service.checkGamePlatform("xbox");
        // Assert
        assertEquals(PLATFORM.Xbox, returnedPlatform);

        // Assign & Act
        returnedPlatform = service.checkGamePlatform("switch");
        assertEquals(PLATFORM.Switch, returnedPlatform);
    }

    @Test
    public void testRandomGame() {
        Game defaultGame = service.randomGame();
        assertEquals(service.getGame("001").getName(), defaultGame.getName());
    }
}