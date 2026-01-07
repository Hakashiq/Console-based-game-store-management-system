import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    private final String URL = "jdbc:mysql://localhost:3306/gamecenter";
    private final String USER = "root";
    private final String PASSWORD = "root123";

    private Connection conn;

    public GameDAO() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("✅ Connected to GameShop Database!");
    }

    public List<Game> getAllGames() throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Game g = new Game(
                        rs.getInt("game_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                games.add(g);
            }
        }
        return games;
    }

    public boolean insertGame(Game game) throws SQLException {
        String sql = "INSERT INTO games (name, category, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, game.getName());
            pstmt.setString(2, game.getCategory());
            pstmt.setDouble(3, game.getPrice());
            pstmt.setInt(4, game.getStock());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateGame(Game game) throws SQLException {
        String sql = "UPDATE games SET price = ?, stock = ? WHERE game_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, game.getPrice());
            pstmt.setInt(2, game.getStock());
            pstmt.setInt(3, game.getGameId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteGame(int gameId) throws SQLException {
        String sql = "DELETE FROM games WHERE game_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gameId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
