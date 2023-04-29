package state;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.BaseView;

public class FinalNumberState extends BaseState {
    public FinalNumberState(BaseView view) {
        super(view);
        view.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) { 
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
				} else if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) {
                    // convert key code to integer by subtracting the 0th
                    int number = e.getKeyCode() - KeyEvent.VK_0; 
                    view.board.setCell(view.selectedCellY, view.selectedCellX, number, true);
                }
            }
        });
        view.requestFocusInWindow(); 
    }
}
