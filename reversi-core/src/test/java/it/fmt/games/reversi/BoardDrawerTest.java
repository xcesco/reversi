package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardDrawerTest {

    @Test
    void drawBoardTest() throws Exception {
        BoardDrawer boardDrawer = new BoardDrawer(BoardReader.read("available_moves01"));
        boardDrawer.drawBoard();
    }
}