import java.sql.*;
import java.util.Scanner;

public class games {
    private static final String URL = "jdbc:mysql://localhost:3306/gamecenter";
    private static final String USER = "root";
    private static final String PASSWORD = "hakashiq";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Connected to GameShop Database!");

            while (true) {
                System.out.println("\n🎮 Game CD Shop - Menu:");
                System.out.println("1️⃣ View Games");
                System.out.println("2️⃣ Insert New Game");
                System.out.println("3️⃣ Update Game Details");
                System.out.println("4️⃣ Delete Game");
                System.out.println("5️⃣ Exit");
                System.out.print("🔹 Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewGames(conn);
                        break;
                    case 2:
                        insertGame(conn, scanner);
                        break;
                    case 3:
                        updateGame(conn, scanner);
                        break;
                    case 4:
                        deleteGame(conn, scanner);
                        break;
                    case 5:
                        System.out.println("🚪 Exiting... Thank you!");
                        return;
                    default:
                        System.out.println("❌ Invalid choice! Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 📌 View all games
    private static void viewGames(Connection conn) throws SQLException {
        String sql = "SELECT * FROM games";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n🎮 Available Games:");
            while (rs.next()) {
                System.out.println("🆔 " + rs.getInt("game_id") +
                        " | 🎮 " + rs.getString("name") +
                        " | 🏷️ " + rs.getString("category") +
                        " | 💰 $" + rs.getDouble("price") +
                        " | 📦 Stock: " + rs.getInt("stock"));
            }
        }
    }

    // 📌 Insert a new game
    private static void insertGame(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("🎮 Enter game name: ");
        String name = scanner.nextLine();
        System.out.print("🎮 Enter category (PS4/PS5): ");
        String category = scanner.nextLine();
        System.out.print("💰 Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("📦 Enter stock quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String sql = "INSERT INTO games (name, category, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stock);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Game added successfully!" : "❌ Failed to add game.");
        }
    }

    // 📌 Update game price & stock
    private static void updateGame(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("🆔 Enter game ID to update: ");
        int gameId = scanner.nextInt();
        System.out.print("💰 Enter new price: ");
        double newPrice = scanner.nextDouble();
        System.out.print("📦 Enter new stock: ");
        int newStock = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String sql = "UPDATE games SET price = ?, stock = ? WHERE game_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, newStock);
            pstmt.setInt(3, gameId);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Game updated successfully!" : "❌ Game ID not found.");
        }
    }

    // 📌 Delete a game
    private static void deleteGame(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("🆔 Enter game ID to delete: ");
        int gameId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String sql = "DELETE FROM games WHERE game_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gameId);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Game deleted successfully!" : "❌ Game ID not found.");
        }
    }
}
