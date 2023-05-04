package builder;

import board.Board;
import board.CellType;

import java.util.List;

public class NormalBoardBuilder implements IBoardBuilder {

    private int _col;
    private int _row;
    public NormalBoardBuilder(int col, int row) {
        _col = col;
        _row = row;
    }

    @Override
    public Board build(List<Integer> nums) {

        Board board = new Board(_col, _row);
        int index = 0;
        for (int y = 0; y < _row; y++) {
            for (int x = 0; x < _col; x++) {
                board.setCell(y, x, nums.get(index++), CellType.FINAL);
            }
        }

        return board;
    }
}
