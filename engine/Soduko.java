package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import board.Board;
import board.BoardFactory;
import state.BaseState;
import state.FinalNumberState;
import state.HelperNumberState;
import view.BaseView;
import view.FinalViewDecorator;
import view.GameFrame;
import view.HelperViewDecorator;

public class Soduko {
    Board board;
    GameFrame window;
    BaseView view;
    BaseState state;

    public Soduko() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.createBoard();
        view = new FinalViewDecorator(board);
        window = new GameFrame(view);
        state = new FinalNumberState(this);
        
        window.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(); 
                int y = e.getY();
                
                view.handleClick(y, x);
            }
        });
    }

    public void switchState() {
        if(state instanceof FinalNumberState) {
            state.detach(view);
            view = new HelperViewDecorator(board);
            window.setActiveView(view);
            state = new HelperNumberState(this);
        } else {
            state.detach(view);
            view = new FinalViewDecorator(board);
            window.setActiveView(view);
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
}
