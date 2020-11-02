package DAO;

import DTO.Game;

import java.util.List;
import java.util.Map;

public interface GameLibraryInterface {
    String generateId();
    Game createGame(Game newGame);
    Game deleteGame(String gameId);
    Game getGame(String gameId);
    List<Game> viewLibrary();
    int getSize();
    void saveTrigger() throws LibraryNotFound;
    void saveFile(Map<String, Game> games) throws LibraryNotFound;
    void loadFile() throws LibraryNotFound;
    String packUp(Game game);
    Game unPack(String gameString);
}
