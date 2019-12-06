package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static it.fmt.games.reversi.Coordinates.of;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnemyPiecesToInvertFinderTest {

    @Test
    void find00() throws Exception {
        assertThrows(InvalidPieceSelectedException.class, () -> {
            new EnemyPiecesToInvertFinder(null, of("a1"), Piece.EMPTY);
        });
    }

    @Test
    void find01() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("d1"), "enemy_pieces_to_invert_finder01", of("c1"));
    }

    @Test
    void find02() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("d1"), "enemy_pieces_to_invert_finder02");
    }

    @Test
    void find03() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_invert_finder03", of("d4"));
    }

    @Test
    void find03Player2() throws Exception {
        checkEnemyPieceFinderForPlayer2(of("f4"), "enemy_pieces_to_invert_finder03", of("e4"));
    }

    @Test
    void find04() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_invert_finder04", of("c3"), of("d4"));
    }

    @Test
    void find05() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_invert_finder05", of("c2"), of("c3"), of("d4"));
    }

    @Test
    void find06() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_invert_finder06", of("c2"), of("c3"), of("d3"), of("d4"), of("e2"));
    }

    private void checkEnemyPieceFinderForPlayer1(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkEnemyPieceFinder(Piece.PLAYER_1, newMoveCoordinate, fileName, coordinates);
    }

    private void checkEnemyPieceFinderForPlayer2(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkEnemyPieceFinder(Piece.PLAYER_2, newMoveCoordinate, fileName, coordinates);
    }

    private void checkEnemyPieceFinder(Piece activePiece, Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        List<Coordinates> aspectedResult0 = Arrays.asList(coordinates);
        Board board = BoardReader.read(fileName);
        EnemyPiecesToInvertFinder finder = new EnemyPiecesToInvertFinder(board, newMoveCoordinate, activePiece);
        List<Coordinates> capturedPiecesCoords = finder.find();
        assertThat(capturedPiecesCoords.size(), equalTo(aspectedResult0.size()));
        assertEquals(capturedPiecesCoords, aspectedResult0);
    }
}