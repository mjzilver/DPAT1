package engine;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import board.BoardFactory;
import view.GameFrame;

public class Soduko {
    Board board;
    int currentState = 1;
    int currentView = 1;
    boolean gameLoop = true;
    GameFrame window = new GameFrame();

    private int DRAW_DELAY = 1000 / 60;
	private Timer drawTimer;
	    
    public Soduko() {
        BoardFactory boardFactory = new BoardFactory();
        this.board = boardFactory.createBoard();

        this.setWindow();
        this.start();
    }

    private void setWindow() {
  
    }

    public void start() {
		drawTimer = new Timer(DRAW_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				window.repaint();
			}
		});

		drawTimer.setRepeats(true);
		drawTimer.start();
    }

    public void selectCell(int row, int col) {}
    public void enterNumber(int num) {}
    public void removeNumber() {}
    public void switchView() {}
    public void switchEditor() {}
}
