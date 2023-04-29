package state;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import engine.Soduko;
import view.BaseView;

public class HelperNumberState extends BaseState {
    public HelperNumberState(Soduko soduko) {
        super(soduko);
        BaseView view = soduko.getView();
        view.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) { 
                if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) {
                    // convert key code to integer by subtracting the 0th
                    int number = e.getKeyCode() - KeyEvent.VK_0; 
                    soduko.getBoard().setCell(view.selectedCellY, view.selectedCellX, number, false);
                }
            }
        });
        view.requestFocusInWindow(); 
    }
}
