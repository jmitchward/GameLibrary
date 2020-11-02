package DAO;

import DTO.GENRE;
import DTO.Game;
import DTO.PLATFORM;
import DTO.RATING;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameLibraryTest {
    GameLibrary testDao;

    public GameLibraryTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception, LibraryNotFound {
        String testFile = "test_library.txt";
        new FileWriter(testFile);
        testDao = new GameLibrary(testFile);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGame() {
        // Assign
        // Create a game
        String newId = testDao.generateId();
        Game newGame = new Game(newId);
        newGame.setName("Test");
        newGame.setPlayers(1);
        newGame.setPlatform(PLATFORM.Playstation);
        newGame.setGenre(GENRE.ACTION);
        newGame.setRating(RATING.FIVE);
        newGame.setDatePurchased(LocalDate.now());
        // Act
        testDao.createGame(newGame);
        Game addedGame = testDao.getGame(newGame.getGameId());
                // Assert
        assertEquals(addedGame.getGameId(), newGame.getGameId(), "Game ID");
        assertEquals(addedGame.getGenre(), newGame.getGenre(), "Game Genre");
        assertEquals(addedGame.getDatePurchased(), newGame.getDatePurchased(), "Game Date");
        assertEquals(addedGame.getName(), newGame.getName(), "Game Name");
        assertEquals(addedGame.getPlatform(), newGame.getPlatform(), "Game Platform");
        assertEquals(addedGame.getPlayers(), newGame.getPlayers(), "Game Players");
    }

    @Test
    public void testDeleteGame() {
        // Assign
        String newId = testDao.generateId();
        Game newGame = new Game(newId);
        newGame.setName("Test");
        newGame.setPlayers(1);
        newGame.setPlatform(PLATFORM.Playstation);
        newGame.setGenre(GENRE.ACTION);
        newGame.setRating(RATING.FIVE);
        newGame.setDatePurchased(LocalDate.now());
        testDao.createGame(newGame);
        // Second
        String newIdTwo = testDao.generateId();
        Game newGameTwo = new Game(newIdTwo);
        newGameTwo.setName("TestTwo");
        newGameTwo.setPlayers(2);
        newGameTwo.setPlatform(PLATFORM.Playstation);
        newGameTwo.setGenre(GENRE.ACTION);
        newGameTwo.setRating(RATING.FIVE);
        newGameTwo.setDatePurchased(LocalDate.now());
        testDao.createGame(newGameTwo);
        // Assert
        assertEquals(2, testDao.gameLibrary.size(), "They're There.");
        assertTrue(testDao.viewLibrary().contains(newGame), "One is there.");
        assertTrue(testDao.viewLibrary().contains(newGameTwo), "Two is there.");
        // Act
        testDao.deleteGame(newGame.getGameId());
        assertEquals(1, testDao.gameLibrary.size(), "Two is there.");
        assertFalse(testDao.viewLibrary().contains(newGame), "One is not.");
        assertTrue(testDao.viewLibrary().contains(newGameTwo), "Two is there.");
    }

    @Test
    public void testGetGame() {
        // Assign
        String newId = testDao.generateId();
        Game newGame = new Game(newId);
        newGame.setName("Test");
        newGame.setPlayers(1);
        newGame.setPlatform(PLATFORM.Playstation);
        newGame.setGenre(GENRE.ACTION);
        newGame.setRating(RATING.FIVE);
        newGame.setDatePurchased(LocalDate.now());
        testDao.createGame(newGame);
        // Act
        Game retrievedGame = testDao.getGame(newId);
        // Assert
        assertEquals(retrievedGame.getGameId(), newGame.getGameId(), "Game ID");
        assertEquals(retrievedGame.getGenre(), newGame.getGenre(), "Game Genre");
        assertEquals(retrievedGame.getDatePurchased(), newGame.getDatePurchased(), "Game Date");
        assertEquals(retrievedGame.getName(), newGame.getName(), "Game Name");
        assertEquals(retrievedGame.getPlatform(), newGame.getPlatform(), "Game Platform");
        assertEquals(retrievedGame.getPlayers(), newGame.getPlayers(), "Game Players");
    }

    @Test
    public void testGetAllGames() {
        // Assign
        String newId = testDao.generateId();
        Game newGame = new Game(newId);
        newGame.setName("Test");
        newGame.setPlayers(1);
        newGame.setPlatform(PLATFORM.Playstation);
        newGame.setGenre(GENRE.ACTION);
        newGame.setRating(RATING.FIVE);
        newGame.setDatePurchased(LocalDate.now());
        testDao.createGame(newGame);
        // Second
        String newIdTwo = testDao.generateId();
        Game newGameTwo = new Game(newIdTwo);
        newGameTwo.setName("TestTwo");
        newGameTwo.setPlayers(2);
        newGameTwo.setPlatform(PLATFORM.Playstation);
        newGameTwo.setGenre(GENRE.ACTION);
        newGameTwo.setRating(RATING.FIVE);
        newGameTwo.setDatePurchased(LocalDate.now());
        testDao.createGame(newGameTwo);
        // Act
        List<Game> games = testDao.viewLibrary();
        // Assert
        assertEquals(2, games.size(), "They're There.");
        assertTrue(games.contains(newGame), "One is there.");
        assertTrue(games.contains(newGameTwo), "Two is there.");
    }

    @Test
    public void testGenerateId() {
        // Assign
        String newId = testDao.generateId();
        Game newGame = new Game(newId);
        newGame.setName("Test");
        newGame.setPlayers(1);
        newGame.setPlatform(PLATFORM.Playstation);
        newGame.setGenre(GENRE.ACTION);
        newGame.setRating(RATING.FIVE);
        newGame.setDatePurchased(LocalDate.now());
        testDao.createGame(newGame);
        String newIdTwo = testDao.generateId();
        Game newGameTwo = new Game(newIdTwo);
        newGameTwo.setName("TestTwo");
        newGameTwo.setPlayers(2);
        newGameTwo.setPlatform(PLATFORM.Playstation);
        newGameTwo.setGenre(GENRE.ACTION);
        newGameTwo.setRating(RATING.FIVE);
        newGameTwo.setDatePurchased(LocalDate.now());
        testDao.createGame(newGameTwo);
        // Assert
        assertNotEquals(newId, newIdTwo);
        // Act
        String newIdThree = testDao.generateId();
        // Assert
        assertNotEquals(newId, newIdThree);
        assertNotEquals(newId, newIdThree);
    }




}
