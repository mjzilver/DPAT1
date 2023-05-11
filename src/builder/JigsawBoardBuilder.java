package builder;

import java.util.List;

import board.Board;

public class JigsawBoardBuilder implements IBoardBuilder {

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(0, 0, 0, 0, 0);

        return board;
    }
}
