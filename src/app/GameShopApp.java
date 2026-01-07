package app;

import dao.GameDAO;
import models.Game;
import java.util.*;
import java.sql.SQLException;


public class GameShopApp {

    public void start() {
        Scanner sc = new Scanner(System.in);

        try {
            GameDAO dao = new GameDAO();

            while (true) {
                System.out.println("\n🎮 Game CD Shop Menu:");
                System.out.println("1. View Games");
                System.out.println("2. Insert Game");
                System.out.println("3. Update Game");
                System.out.println("4. Delete Game");
                System.out.println("5. Exit");
                System.out.print("Choose: ");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        List<Game> games = dao.getAllGames();
                        games.forEach(g ->
                                System.out.println("[" + g.getGameId() + "] "
                                        + g.getName() + " | "
                                        + g.getCategory() + " | "
                                        + g.getPrice() + " | Stock: "
                                        + g.getStock())
                        );
                        break;

                    case 2:
                        Game g = new Game();
                        System.out.print("Enter Name: ");
                        g.setName(sc.nextLine());
                        System.out.print("Enter Category: ");
                        g.setCategory(sc.nextLine());
                        System.out.print("Enter Price: ");
                        g.setPrice(sc.nextDouble());
                        System.out.print("Enter Stock: ");
                        g.setStock(sc.nextInt());
                        sc.nextLine();

                        System.out.println(
                                dao.insertGame(g)
                                        ? "✔ Game Added!"
                                        : "❌ Failed!"
                        );
                        break;

                    case 3:
                        Game up = new Game();
                        System.out.print("Enter Game ID: ");
                        up.setGameId(sc.nextInt());
                        System.out.print("New Price: ");
                        up.setPrice(sc.nextDouble());
                        System.out.print("New Stock: ");
                        up.setStock(sc.nextInt());
                        sc.nextLine();

                        System.out.println(
                                dao.updateGame(up)
                                        ? "✔ Updated!"
                                        : "❌ Not Found!"
                        );
                        break;

                    case 4:
                        System.out.print("Enter Game ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.println(
                                dao.deleteGame(id)
                                        ? "✔ Deleted!"
                                        : "❌ Not Found!"
                        );
                        break;

                    case 5:
                        System.out.println("👋 Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
