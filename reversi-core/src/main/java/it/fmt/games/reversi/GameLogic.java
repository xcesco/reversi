package it.fmt.games.reversi;

import java.util.Arrays;
import java.util.List;

import static it.fmt.games.reversi.Coordinates.of;

public class GameLogic {
    private Board board;
    private Player currentPlayer;
    private Player otherPlayer;

    public Board getBoard() {
        return board;
    }

    public GameLogic(Board board) {
        this.board = board;
    }

    public GameLogic() {
        this.board = new Board();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    private void insertPieces(Piece piece, List<Coordinates> coordsList) {
        InsertPieceOperation insertPieceOperation =new InsertPieceOperation(board, piece);
        board=insertPieceOperation.insert(coordsList);
    }

    public void initializeBoard() {
        insertPieces(Piece.PLAYER_1, Arrays.asList(of("e4"), of("d5")));
        insertPieces(Piece.PLAYER_2, Arrays.asList(of("d4"), of("e5")));

        currentPlayer = new Player1();
        otherPlayer = new Player2();
    }

    public AvailableMoves findMovesForPlayers(){
        AvailableMovesFinder finderCurrentPlayer = new AvailableMovesFinder(board , currentPlayer.getPiece());
        AvailableMovesFinder finderOtherPlayer = new AvailableMovesFinder(board , otherPlayer.getPiece());
        List<Coordinates> list1 = finderCurrentPlayer.findMoves();
        return new AvailableMoves(list1,finderOtherPlayer.findMoves());
    }

    public void insertSelectedMove (Coordinates coord){
        Piece piece=this.currentPlayer.getPiece();
        EnemyPiecesToCaptureFinder enemyPiecesFinder = new EnemyPiecesToCaptureFinder(board,coord,piece);
        List<Coordinates> piecesToInsert = enemyPiecesFinder.find();
        piecesToInsert.add(coord);
        insertPieces(piece, piecesToInsert);
    }

    public void switchPlayer(){
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }
}
