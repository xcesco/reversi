package it.fmt.games.reversi.console;

import it.fmt.games.reversi.*;
import it.fmt.games.reversi.model.*;

import java.util.List;
import java.util.Scanner;

public class App extends TextDrawer implements UserInputReader {
    static final String FMT_REVERSI = "  ****************** FMT REVERSI ******************" + NEW_LINE;
    static final String P1_VS_P2 = "  ************** PLAYER_1 vs PLAYER_2 *************" + NEW_LINE;
    static final String P1_VS_CPU = "  **************** PLAYER_1 vs CPU ****************" + NEW_LINE;
    static final String CPU_VS_P2 = "  **************** CPU vs PLAYER_2 ****************" + NEW_LINE;
    static final String CPU_VS_CPU = "  ****************** CPU vs CPU *******************" + NEW_LINE;

    private final Scanner scanner;
    private static int choise = 0;
    private Player1 player1;
    private Player2 player2;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        do {
            App app = new App(new Scanner(System.in));
            app.run();
        } while (choise != 0 && NewGame.playAgain());
    }

    private void run() {
        print(FMT_REVERSI);
        choise = GameTypeSelector.selectGameType(scanner);

        if (choise == 0) {
            return;
        }

        print(NEW_LINE);
        definePlayers(choise);
        Reversi reversi = new Reversi(new ConsoleRenderer(), this, player1, player2);
        reversi.play();
    }

    private void definePlayers(int choise) {
        switch (choise) {
            case 1:
                print(P1_VS_P2);
                this.player1 = PlayerFactory.createHumanPlayer1();
                this.player2 = PlayerFactory.createHumanPlayer2();
                break;
            case 2:
                print(P1_VS_CPU);
                this.player1 = PlayerFactory.createHumanPlayer1();
                this.player2 = PlayerFactory.createRoboPlayer2(DecisionHandlerType.RANDOM);
                break;
            case 3:
                print(CPU_VS_P2);
                this.player1 = PlayerFactory.createRoboPlayer1(DecisionHandlerType.RANDOM);
                this.player2 = PlayerFactory.createHumanPlayer2();
                break;
            case 4:
                print(CPU_VS_CPU);
                this.player1 = PlayerFactory.createRoboPlayer1(DecisionHandlerType.RANDOM);
                this.player2 = PlayerFactory.createRoboPlayer2(DecisionHandlerType.RANDOM);
                break;
        }
    }

    @Override
    public Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves) {
        Coordinates coords;

        do {
            print(String.format("- %s, insert move: ", currentPlayer.getPiece()));
            coords = Coordinates.of(scanner.next());

        } while ((!coords.isValid() && availableMoves.indexOf(coords) == -1));
        return coords;
    }
}
