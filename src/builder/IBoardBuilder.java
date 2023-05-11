package builder;

import java.util.List;

import board.Board;

public interface IBoardBuilder {
    public Board build(List<String> fileContent);
}
