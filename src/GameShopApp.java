import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GameShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            GameDAO dao = new GameDAO();

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
                        List<Game> games = dao.getAllGames();
                        System.out.println("\n🎮 Available Games:");
                        for (Game g : games) {
                            System.out.println("🆔 " + g.getGameId() +
                                    " | 🎮 " + g.getName() +
                                    " | 🏷️ " + g.getCategory() +
                                    " | 💰 $" + g.getPrice() +
                                    " | 📦 Stock: " + g.getStock());
                        }
                        break;

                    case 2:
                        Game newGame = new Game();
                        System.out.print("🎮 Enter game name: ");
                        newGame.setName(scanner.nextLine());
                        System.out.print("🎮 Enter category (PS4/PS5): ");
                        newGame.setCategory(scanner.nextLine());
                        System.out.print("💰 Enter price: ");
                        newGame.setPrice(scanner.nextDouble());
                        System.out.print("📦 Enter stock quantity: ");
                        newGame.setStock(scanner.nextInt());
                        scanner.nextLine();
                        System.out.println(dao.insertGame(newGame) ? "✅ Game added successfully!" : "❌ Failed to add game.");
                        break;

                    case 3:
                        Game updatedGame = new Game();
                        System.out.print("🆔 Enter game ID to update: ");
                        updatedGame.setGameId(scanner.nextInt());
                        System.out.print("💰 Enter new price: ");
                        updatedGame.setPrice(scanner.nextDouble());
                        System.out.print("📦 Enter new stock: ");
                        updatedGame.setStock(scanner.nextInt());
                        scanner.nextLine();
                        System.out.println(dao.updateGame(updatedGame) ? "✅ Game updated successfully!" : "❌ Game ID not found.");
                        break;

                    case 4:
                        System.out.print("🆔 Enter game ID to delete: ");
                        int gameId = scanner.nextInt();
                         scanner.nextLine();
                        System.out.println(dao.deleteGame(gameId) ? "✅ Game deleted successfully!" : "❌ Game ID not found.");
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
}