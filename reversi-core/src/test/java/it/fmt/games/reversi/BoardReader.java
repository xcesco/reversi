package it.fmt.games.reversi;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * This class allows to read a txt file which contains board status. The following characters are used to represent
 * player pieces:
 * <ul>
 *    <li><code>o</code> for player 1</li>
 *    <li><code>x</code> for player 2</li>
 *    <li><code>.</code> for empty cell</li>
 * </ul>
 * If you need to put different board in a single file, just separate them with one or more <code>=</code> (in a line).
 *
 * <strong>Other characters are ignored.</strong>
 *
 * <p>Just an example of a file</p>
 *
 * <pre>
 *   A B C D E F G H
 * 1 x . . . . . . .
 * 2 . . . . . . . .
 * 3 . . . . . . . .
 * 4 . . . . . . . .
 * 5 . . . . . . . .
 * 6 . . . . . . . .
 * 7 . . . . . . . .
 * 8 . . . . . . . .
 * ========
 *   A B C D E F G H
 * 1 x . . . . . . .
 * 2 . . . . . . . .
 * 3 . . . . . . . .
 * 4 . . . . . . . .
 * 5 . . . . . . . .
 * 6 . . . . . . x .
 * 7 . . . . . . . .
 * 8 . . . . . . . .
 * </pre>
 * <p>
 * You can use {@link #read(String)} to read a file with a single board snapshot, or {@link #readBoards(String)} to read a file
 * with multiple snapshots.
 */
public class BoardReader {

    public static final String ONLY_VALID_CHARS_REGEX = "[^\\.ox\\=]";
    public static final String BOARD_SEPARATOR_REGEX = "\\=";
    public static final String MULITPLE_BOARD_SEPARATOR_REGEX = "\\=+";

    /**
     * See javadoc of class {@link BoardReader}
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Board[] readBoards(String fileName) throws Exception {
        URL url = BoardReader.class.getClassLoader().getResource("boards/" + fileName + ".txt");
        Path resPath = Paths.get(url.toURI());

        String fileContent = Files.readString(resPath);
        String[] boardBuffer = removeMultipleBoardSeparators(fileContent).replaceAll(ONLY_VALID_CHARS_REGEX, "").split(BOARD_SEPARATOR_REGEX);
        return Arrays.stream(boardBuffer)
                .map(BoardReader::parseSingleBoard)
                .toArray(Board[]::new);

    }

    /**
     * See javadoc of class {@link BoardReader}
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Board read(String fileName) throws Exception {
        Board[] result = readBoards(fileName);

        if (result == null || result.length != 1) {
            throw new InvalidBoardSnapshotException(String.format("File %s does not contains a valid snapshot", fileName));
        }

        return result[0];
    }

    private static String removeMultipleBoardSeparators(String content) {
        return content.replaceAll(MULITPLE_BOARD_SEPARATOR_REGEX, BOARD_SEPARATOR_REGEX);
    }

    private static Board parseSingleBoard(String content) {
        if (content.length() != Board.BOARD_SIZE * Board.BOARD_SIZE) {
            throw new RuntimeException("Invalid marker in configuration file");
        }
        Cell[] cells = IntStream.range(0, content.length())
                .mapToObj(index -> convertSingleCharToCell(content, index))
                .toArray(Cell[]::new);
        return new Board(cells);
    }

    private static Cell convertSingleCharToCell(String fileContent, int index) {
        char c = fileContent.charAt(index);
        Coordinates coordinates = Coordinates.of(index / 8, index % 8);
        Piece piece;
        switch (c) {
            case 'o':
                piece = Piece.PLAYER_1;
                break;
            case 'x':
                piece = Piece.PLAYER_2;
                break;
            case '.':
            default:
                piece = Piece.EMPTY;
                break;
        }
        return new Cell(coordinates, piece);
    }
}
