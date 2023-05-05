package builder;

import board.Board;

import java.util.List;

public interface IBoardBuilder {
    public Board build(List<Integer> nums);
}
