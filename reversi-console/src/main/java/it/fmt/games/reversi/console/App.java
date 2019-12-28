package it.fmt.games.reversi.console;

import it.fmt.games.reversi.*;
import it.fmt.games.reversi.model.*;

import java.util.List;
import java.util.Scanner;

import static it.fmt.games.reversi.console.drawers.TextDrawer.*;

public class App implements UserInputReader {
    static final String FMT_REVERSI = "  ****************** FMT REVERSI ******************";

    public Scanner getScanner() {
        return scanner;
    }

    private final Scanner scanner;
    private static int choice;

    private Player1 player1;
    private Player2 player2;

    public void setPlayer1(Player1 player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Player2 player2) {
        this.player2 = player2;
    }



    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        do {
            App app = new App(scanner);
            app.run();
        } while (choice != 0 && PlayAgainOrExitSelector.playAgain(scanner));
    }

    private void run() {
        println(FMT_REVERSI);
        choice = PlayersSelector.selectPlayers(this);

        if (choice == 0) {
            return;
        }

        Reversi reversi = new Reversi(new ConsoleRenderer(), this, player1, player2);
        reversi.play();
    }


    @Override
    public Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves) {
        Coordinates coords;

        do {
            print(2,String.format("- %s, insert move: ", currentPlayer.getPiece()));
            coords = Coordinates.of(scanner.nextLine().trim());

        } while (!isValidMoves(coords, availableMoves));
        return coords;
    }

    private boolean isValidMoves(Coordinates coordinates, List<Coordinates> validValues) {
        return coordinates.isValid() && validValues.indexOf(coordinates)>=0;
    }
}
