package builder;

import board.Board;
import board.CellType;

import java.util.List;

public class NormalBoardBuilder implements IBoardBuilder {

    private final int _col;
    private final int _row;
    private final int _boxWidth;
    private final int _boxHeight;
    private final int _maxNumber;

    public NormalBoardBuilder(int col, int row, int boxWidth, int boxHeight, int maxNumber) {
        _col = col;
        _row = row;
        _boxWidth = boxWidth;
        _boxHeight = boxHeight;
        _maxNumber = maxNumber;
    }

    @Override
    public Board build(List<Integer> nums) {
        Board board = new Board(_col, _row, _boxWidth, _boxHeight, _maxNumber);
        int index = 0;
        for (int y = 0; y < _row; y++) {
            for (int x = 0; x < _col; x++) {
                board.setCell(y, x, nums.get(index++), CellType.KNOWN);
            }
        }

        return board;
    }
}
