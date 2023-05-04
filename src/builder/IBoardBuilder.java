package builder;

import board.Board;

import java.util.List;

public interface IBoardBuilder {
    Board build(List<Integer> nums);
}
