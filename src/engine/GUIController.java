package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import state.BaseState;
import state.FinalNumberState;
import state.HelperNumberState;
import view.BaseView;
import view.ControlView;
import view.FinalViewDecorator;
import view.HelperViewDecorator;
import view.GameFrame;

public class GUIController {
    private BoardController boardController;
    private GameFrame window;
    private BaseView view;
    private BaseState state;

    public GUIController() {
        boardController = new BoardController();
        view = new FinalViewDecorator(boardController.getBoard());
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
        state.detach(view);
        BaseView newView;
        if (state instanceof FinalNumberState) {
            newView = new HelperViewDecorator(boardController.getBoard());
            state = new HelperNumberState(this);
        } else {
            newView = new FinalViewDecorator(boardController.getBoard());
            state = new FinalNumberState(this);
        }
        window.switchView(view, newView);
        this.view = newView;
    }

    public void openBoard(String name) {
        boardController.openBoard(name);
        state.detach(view);
        BaseView newView = new FinalViewDecorator(boardController.getBoard());
        window.switchView(view, newView);
        this.view = newView;
        state = new FinalNumberState(this);
    }

    public BaseView getView() {
        return view;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public GameFrame getWindow() {
        return window;
    }
}
