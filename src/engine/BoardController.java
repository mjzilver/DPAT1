package engine;

import board.Board;
import board.BoardFactory;
import visitor.BoardVisitor;
import visitor.CheckVisitor;

public class BoardController {
    private Board board;
    private BoardFactory boardFactory;
    private BoardVisitor visitor;

    public BoardController() {
        boardFactory = new BoardFactory();
        visitor = new CheckVisitor();
        openBoard("puzzle.4x4");
    }

    public void openBoard(String name) {
        board = boardFactory.createBoard(name);
    }

    public Board getBoard() {
        return board;
    }

    public void checkAll() {
        board.uncheck();
        board.accept(visitor);
        board.notifyObservers();
    }

    public void uncheckAll() {
        board.uncheck();
        board.notifyObservers();
    }
}
