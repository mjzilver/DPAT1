package state;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import engine.Sudoku;
import view.BaseView;

public abstract class BaseState {
    protected Sudoku sudoku;
    protected KeyAdapter keyAdapter; 

    BaseState(Sudoku sudoku) {
        this.sudoku = sudoku;
        BaseView view = sudoku.getView();
        view.addKeyListener(makeKeyAdapter());
        view.requestFocusInWindow(); 
    }

    protected abstract void handleNumber(int number);

    protected KeyAdapter makeKeyAdapter() {
        return this.keyAdapter = new KeyAdapter() {
			public void keyPressed(KeyEvent e) { 
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
				} else if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_R) {
                    sudoku.switchState();
                } else if (e.getKeyCode() == KeyEvent.VK_C) {
                    sudoku.checkAll();
                } else if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) {
                    int number = e.getKeyCode() - KeyEvent.VK_0; 
                    handleNumber(number);
                }
            }
        };
    }

    public void detach(BaseView view) {
        view.removeKeyListener(this.keyAdapter);
    }

    public void attach(BaseView view) {
        view.addKeyListener(this.keyAdapter);
    }
}
