package builder;

import board.Board;
import engine.Sudoku;

import java.util.List;

public interface IBoardBuilder {
    Board build(List<Integer> nums);
}
