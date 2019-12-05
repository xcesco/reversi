package it.fmt.games.reversi;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class BoardReader {

//    public static Cell[] readFile(String boardConfig) throws Exception {
//        BoardReader reader=new BoardReader();
//        return reader.read(boardConfig);
//    }

    /**
     * <code>o</code> for player 1, <code>x</code> for player 2
     * @param fileName
     * @return
     * @throws Exception
     */
    public static  Cell[] read(String fileName) throws Exception {
        URL url = BoardReader.class.getClassLoader().getResource("boards/"+fileName+".txt");
        Path resPath = Paths.get(url.toURI());

        final String fileContent= Files.readString(resPath).replaceAll("[^\\.ox]","");
        if(fileContent.length()!=Board.BOARD_SIZE*Board.BOARD_SIZE){
            throw new RuntimeException("Invalid marker in configuration file");
        }
        Cell[] cells = IntStream.range(0, fileContent.length())
                .mapToObj(index ->{
                    char c= fileContent.charAt(index);
                    Coordinates coordinates=Coordinates.of(index/8,index%8);
                    Piece piece;
                    switch (c) {
                        case 'o':
                            piece=Piece.PLAYER_1;
                            break;
                        case 'x':
                            piece=Piece.PLAYER_2;
                            break;
                        default:
                            piece=Piece.EMPTY;
                            break;
                    }
                    return new Cell(coordinates,piece);
        }).toArray(Cell[]::new);
        return cells;

    }
}
