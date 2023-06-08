package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import board.Board;
import board.BoardFactory;
import state.BaseState;
import state.FinalNumberState;
import state.HelperNumberState;
import view.*;
import visitor.CheckVisitor;

public class Sudoku {
    private Board board;
    private GameFrame window;
    private BaseView view;
    private BaseState state;
    private CheckVisitor visitor = new CheckVisitor();
    private BoardFactory boardFactory = new BoardFactory();

    public Sudoku() {
        board = boardFactory.createBoard("puzzle4solved.samurai");
        view = new FinalViewDecorator(board);
        window = new GameFrame(view);
        state = new FinalNumberState(this);
        window.addView(new ControlView(this, view.getWidth()));

        window.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                view.handleClick(y, x);
            }
        });
    }

    public void switchState() {
        if (state instanceof FinalNumberState) {
            state.detach(view);
            BaseView newView = new HelperViewDecorator(board);
            window.switchView(view, newView);
            this.view = newView;
            state = new HelperNumberState(this);
        } else {
            state.detach(view);
            BaseView newView = new FinalViewDecorator(board);
            window.switchView(view, newView);
            this.view = newView;
            state = new FinalNumberState(this);
        }
    }

    public BaseView getView() {
        return view;
    }

    public Board getBoard() {
        return board;
    }

    public GameFrame getWindow() {
        return window;
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

    public void openBoard(String name) {
        board = boardFactory.createBoard(name);
        state.detach(view);
        BaseView newView = new FinalViewDecorator(board);
        window.switchView(view, newView);
        this.view = newView;
        state = new FinalNumberState(this);
    }
}
