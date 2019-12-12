package it.fmt.games.reversi.model;

import org.junit.jupiter.api.Test;

public class BoardDrawerTest {

    @Test
    public void drawBoardTest() throws Exception {
        BoardDrawer boardDrawer = new BoardDrawer(BoardReader.read("available_moves01"));
        boardDrawer.drawBoard();
    }
}