package builder;

import board.Board;
import board.CellType;

import java.util.List;

public class NormalBoardBuilder implements IBoardBuilder {

    private int _col;
    private int _row;
    private int _boxWidth;
    private int _boxHeight;
    public NormalBoardBuilder(int col, int row, int boxWidth, int boxHeight) {
        _col = col;
        _row = row;
        _boxWidth = boxWidth;
        _boxHeight = boxHeight;
    }

    @Override
    public Board build(List<Integer> nums) {

        Board board = new Board(_col, _row, _boxWidth, _boxHeight);
        int index = 0;
        for (int y = 0; y < _row; y++) {
            for (int x = 0; x < _col; x++) {
                board.setCell(y, x, nums.get(index++), CellType.FINAL);
            }
        }

        return board;
    }
}
