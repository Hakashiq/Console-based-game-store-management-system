package dao;

import models.Game;
import database.DBConnection;
import java.sql.*;
import java.util.*;

public class GameDAO {

    private Connection conn;

    public GameDAO() {
        conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Connected to GameShop Database!");
        }
    }

    public List<Game> getAllGames() throws SQLException {
        List<Game> list = new ArrayList<>();

        String sql = "SELECT * FROM games";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Game(
                        rs.getInt("game_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    public boolean insertGame(Game g) throws SQLException {
        String sql = "INSERT INTO games(name, category, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getName());
            ps.setString(2, g.getCategory());
            ps.setDouble(3, g.getPrice());
            ps.setInt(4, g.getStock());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateGame(Game g) throws SQLException {
        String sql = "UPDATE games SET price = ?, stock = ? WHERE game_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, g.getPrice());
            ps.setInt(2, g.getStock());
            ps.setInt(3, g.getGameId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteGame(int id) throws SQLException {
        String sql = "DELETE FROM games WHERE game_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
