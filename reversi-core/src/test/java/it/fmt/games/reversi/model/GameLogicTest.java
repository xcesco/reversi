package it.fmt.games.reversi.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static it.fmt.games.reversi.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameLogicTest {

    @Test
    void initBoard() throws Exception {
        GameLogic gameLogic = new GameLogic();
        gameLogic.initialize();
        Board aspectedBoard = BoardReader.read("gameLogicTest");

        assertThat(gameLogic.getBoard(), is(aspectedBoard));
        assertThat(gameLogic.getCurrentPlayer().getPiece(), is(Piece.PLAYER_1));
        assertThat(gameLogic.getOtherPlayer().getPiece(), is(Piece.PLAYER_2));
    }

    @Test
    void findMovesForPlayers() {
        List<Coordinates> aspectedMovesForPlayer1 = Arrays.asList(of("d3"), of("c4"), of("f5"), of("e6"));
        List<Coordinates> aspectedMovesForPlayer2 = Arrays.asList(of("e3"), of("f4"), of("c5"), of("d6"));

        GameLogic gameLogic = new GameLogic();
        gameLogic.initialize();

        AvailableMoves availableMoves = gameLogic.findMovesForPlayers();
        checkAvailableMovesFinder(availableMoves.getMovesCurrentPlayer(), aspectedMovesForPlayer1);
        checkAvailableMovesFinder(availableMoves.getMovesOtherPlayer(), aspectedMovesForPlayer2);
    }

    public void checkAvailableMovesFinder(List<Coordinates> coordinates, List<Coordinates> aspectedCoordinates) {
        assertThat(coordinates.size(), equalTo(aspectedCoordinates.size()));
        assertEquals(coordinates, aspectedCoordinates);
    }

    @Test
    void insertSelectedMove() throws Exception {
        GameLogic gameLogic = new GameLogic();
        gameLogic.initialize();

        AvailableMoves availableMoves = gameLogic.findMovesForPlayers();
        Coordinates player1Move = availableMoves.getMovesCurrentPlayer().get(0);
        gameLogic.insertSelectedMove(player1Move);


        Board aspectedBoard = BoardReader.read("gameLogicTest1");

        assertThat(gameLogic.getBoard(), is(aspectedBoard));
    }

    @Test
    void switchPlayer() {
        GameLogic gameLogic = new GameLogic();
        gameLogic.initialize();

        gameLogic.switchPlayer();
        assertThat(gameLogic.getCurrentPlayer().getPiece(), is(Piece.PLAYER_2));
        assertThat(gameLogic.getOtherPlayer().getPiece(), is(Piece.PLAYER_1));


    }
}