package View;

import DTO.Game;

import java.util.List;

public class LibraryView {
    UserInput getInput;

    public LibraryView(UserInput newInput) {
        this.getInput = newInput;
    }

    public String mainMenu() {
        getInput.print("Welcome.");
        getInput.print("1. Add Game");
        getInput.print("2. Delete Game");
        getInput.print("3. View Game");
        getInput.print("4. Random Game");
        getInput.print("5. View All Games");
        getInput.print("6. Exit");
        return getInput.readString("Selection: ");
    }

    public String getGameId() {
        return getInput.readString("Game ID: ");
    }

    public String getName() {
        return getInput.readString("Name: ");
    }

    public String getDatePurchased() {
        return getInput.readString("Date Purchased: ");
    }

    public String getPlatform() {
        getInput.print("Platforms: ");
        getInput.print("1. Playstation");
        getInput.print("2. Xbox");
        getInput.print("3. PC");
        getInput.print("4. Switch");
        return getInput.readString("Platform: ");
    }

    public int getPlayers() {
        return getInput.readInt("Number of Players: ");
    }

    public String selectGenre() {
        getInput.print("Genres: ");
        getInput.print("1. RPG");
        getInput.print("2. Strategy");
        getInput.print("3. Adventure");
        getInput.print("4. Action");
        getInput.print("5. Simulation");
        getInput.print("6. Racing");
        return getInput.readString("Select a Genre: ");
    }

    public int selectRating() {
        return getInput.readInt("Enter a Rating (1-5): ");
    }

    public String selectPurchaseDate() {
        return getInput.readString("Enter Purchase Date: ");
    }

    public void displayGame(Game game) {
        getInput.print("Name: " + game.getName());
        getInput.print("Date Purchased: " + game.getDatePurchased());
        getInput.print("Platform: " + game.getPlatform());
        getInput.print("# Players: " + game.getPlayers());
        getInput.print("Genre: " + game.getGenre());
        getInput.print("Rating: " + game.getRating());
    }

    public void displayGame(List<Game> games) {
        for (Game game : games) {
            getInput.print("ID: " + game.getGameId() + "| Name: " + game.getName());
        }
    }

    public void displaySuccess(String addedGame, String action) {
        getInput.print(addedGame + " has been " + action);
    }

    public void displayExit() {
        getInput.print("Saving content...");
        getInput.print("Thank you.");
    }
}
