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
        openBoard("puzzle4.samurai");
    }

    public void openBoard(String name) {
        board = boardFactory.createBoard(name);
    }

    public Board getBoard() {
        return board;
    }

    public void checkAll() {
        board.uncheckBoard();
        board.accept(visitor);
        board.notifyObservers();
    }

    public void uncheckAll() {
        board.uncheckBoard();
        board.notifyObservers();
    }
}
