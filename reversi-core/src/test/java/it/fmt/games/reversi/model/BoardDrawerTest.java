package it.fmt.games.reversi.model;

import org.junit.jupiter.api.Test;

class BoardDrawerTest {

    @Test
    void drawBoardTest() throws Exception {
        BoardDrawer boardDrawer = new BoardDrawer(BoardReader.read("available_moves01"));
        boardDrawer.drawBoard();
        BoardDrawer boardDrawer2 = new BoardDrawer(BoardReader.read("available_moves02"));
        boardDrawer2.drawBoard();
    }
}