package DTO;

import java.time.LocalDate;

public class Game {
    private String gameId;
    private String name;
    private LocalDate datePurchased;
    private RATING rating;
    private GENRE genre;
    private PLATFORM platform;
    private int players;


    public Game(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String newId) {
        this.gameId = newId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    public RATING getRating() {
        return rating;
    }

    public void setRating(RATING rating) {
        this.rating = rating;
    }

    public PLATFORM getPlatform() {
        return platform;
    }

    public void setPlatform(PLATFORM platform) {
        this.platform = platform;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public GENRE getGenre() {
        return genre;
    }

    public void setGenre(GENRE newGenre) {
        this.genre = newGenre;
    }
}
