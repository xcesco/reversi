package it.fmt.games.reversi.model;

import org.junit.jupiter.api.Test;

class BoardDrawerTest {

    @Test
    void drawBoardTest() throws Exception {


        BoardDrawer boardDrawer = new BoardDrawer(BoardReader.read("available_moves01"));
        boardDrawer.drawBoard();

//        Score score=new Score(1,12);
//        Player currentPlayer=new Player1();
//        AvailableMoves moves=new AvailableMoves(Arrays.asList(of("d1"),of("b2"),of("f2"),of("a4"),of("g4"),of("b6"),of("f6"),of("d7")), Arrays.asList());
//        Board board= BoardReader.read("available_moves01");
//        GameStatus status=GameStatus.RUNNING;
//
//        GameSnapshot gameSnapshot=new GameSnapshot(score, currentPlayer,moves, board, status);
//        BoardDrawer boardDrawer = new BoardDrawer(gameSnapshot);
//        boardDrawer.drawGameStatus();
    }
}