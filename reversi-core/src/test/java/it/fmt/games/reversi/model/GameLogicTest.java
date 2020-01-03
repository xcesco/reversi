package it.fmt.games.reversi.model;

import it.fmt.games.reversi.support.BoardReader;
import it.fmt.games.reversi.support.TakeFirstUserInputReader;
import it.fmt.games.reversi.PlayerFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static it.fmt.games.reversi.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogicTest {

    @Test
    public void initBoard() throws Exception {
        GameLogicImpl gameLogic = createAndInitializeGameLogic();

        Board aspectedBoard = BoardReader.read("gameLogicTest");

        GameSnapshot gameSnapshot = gameLogic.getGameSnapshot();
        assertThat(gameSnapshot.getBoard(), is(aspectedBoard));
        assertThat(gameSnapshot.getActivePiece(), is(Piece.PLAYER_1));
        assertThat(gameSnapshot.getOtherPlayer(), is(Piece.PLAYER_2));
    }

    @Test
    public void findMovesForPlayers() {
        List<Coordinates> aspectedMovesForPlayer1 = Arrays.asList(of("d3"), of("c4"), of("f5"), of("e6"));
        List<Coordinates> aspectedMovesForPlayer2 = Arrays.asList(of("e3"), of("f4"), of("c5"), of("d6"));

        GameLogicImpl gameLogic = createAndInitializeGameLogic();

        AvailableMoves availableMoves = gameLogic.findMovesForPlayers();
        checkAvailableMovesFinder(availableMoves.getMovesActivePlayer(), aspectedMovesForPlayer1);
        checkAvailableMovesFinder(availableMoves.getMovesOtherPlayer(), aspectedMovesForPlayer2);
    }

    @Test
    public void insertSelectedMove() throws Exception {
        GameLogicImpl gameLogic = createAndInitializeGameLogic();

        AvailableMoves availableMoves = gameLogic.findMovesForPlayers();
        Coordinates player1Move = availableMoves.getMovesActivePlayer().get(0);
        gameLogic.insertSelectedMove(player1Move);

        Board aspectedBoard = BoardReader.read("gameLogicTest1");
        GameSnapshot gameSnapshot = gameLogic.getGameSnapshot();
        assertThat(gameSnapshot.getBoard(), is(aspectedBoard));
    }

    @Test
    public void switchPlayer() {
        GameLogicImpl gameLogic = createAndInitializeGameLogic();

        gameLogic.switchPlayers();
        GameSnapshot gameSnapshot = gameLogic.getGameSnapshot();
        assertThat(gameSnapshot.getActivePiece(), is(Piece.PLAYER_2));
        assertThat(gameSnapshot.getOtherPlayer(), is(Piece.PLAYER_1));
    }

    private void checkAvailableMovesFinder(List<Coordinates> coordinates, List<Coordinates> aspectedCoordinates) {
        assertThat(coordinates.size(), equalTo(aspectedCoordinates.size()));
        assertEquals(coordinates, aspectedCoordinates);
    }

    private GameLogicImpl createAndInitializeGameLogic() {
        GameLogicImpl gameLogic = new GameLogicImpl(PlayerFactory.createCpuPlayer1(), PlayerFactory.createCpuPlayer2(), new TakeFirstUserInputReader());
        gameLogic.initialize();
        return gameLogic;
    }
}