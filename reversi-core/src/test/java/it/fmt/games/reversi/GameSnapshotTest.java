package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static it.fmt.games.reversi.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class GameSnapshotTest {
    @Test
    public void testData() {
        Score score=new Score(20,10);
        Player currentPlayer=new Player1();
        AvailableMoves moves=new AvailableMoves(Arrays.asList(of("a1")), Arrays.asList(of("a2")));
        Board board=new Board();
        GameStatus status=GameStatus.DRAW;
        GameSnapshot gameSnapshot=new GameSnapshot(score, currentPlayer.getPiece(),moves, board, status);

        Score aspectedScore=new Score(20,10);
        Player aspectedCurrentPlayer=new Player1();
        AvailableMoves aspectedMoves=new AvailableMoves(Arrays.asList(of("a1")), Arrays.asList(of("a2")));
        Board aspectedBoard=new Board();
        GameStatus aspectedStatus=GameStatus.DRAW;

        assertThat(gameSnapshot.getAvailableMoves(), equalTo(aspectedMoves));
        assertThat(gameSnapshot.getScore(), equalTo(aspectedScore));
        assertThat(gameSnapshot.getActivePiece(), equalTo(aspectedCurrentPlayer.getPiece()));
        assertThat(gameSnapshot.getBoard(), equalTo(aspectedBoard));
        assertThat(gameSnapshot.getStatus(), equalTo(aspectedStatus));
    }

}