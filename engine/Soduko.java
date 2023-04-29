package engine;

import board.Board;
import board.BoardFactory;
import view.BaseView;
import view.GameFrame;

public class Soduko {
    Board board;
    int currentState = 1;
    int currentView = 1;
    boolean gameLoop = true;
    GameFrame window;
    BaseView baseView;

    public Soduko() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.createBoard();
        baseView = new BaseView(board);
        window = new GameFrame(baseView);
    }

    public void selectCell(int row, int col) {}
    public void enterNumber(int num) {}
    public void removeNumber() {}
    public void switchView() {}
    public void switchEditor() {}
}
