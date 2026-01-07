package models;

public class Game {

    private int gameId;
    private String name;
    private String category;
    private double price;
    private int stock;

    // Constructors
    public Game() {}

    public Game(int gameId, String name, String category, double price, int stock) {
        this.gameId = gameId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters & Setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Optional: For debugging / printing
    @Override
    public String toString() {
        return "Game{" +
                "id=" + gameId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
