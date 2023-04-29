package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import board.Board;
import board.BoardFactory;
import state.BaseState;
import state.FinalNumberState;
import view.BaseView;
import view.GameFrame;

public class Soduko {
    Board board;
    boolean gameLoop = true;
    GameFrame window;
    BaseView view;
    BaseState state;

    public Soduko() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.createBoard();
        view = new BaseView(board);
        window = new GameFrame(view);
        state = new FinalNumberState(view);
        
        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(); 
                int y = e.getY();
                
                view.handleClick(y, x);
            }
        });
    }

    public void switchView() {}
    public void switchEditor() {}
}
