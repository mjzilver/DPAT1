package state;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import engine.Soduko;

public class BaseState {
    BaseState(Soduko soduko) {
        soduko.getView().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) { 
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
				} else if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_R) {
                    soduko.switchState();
                }
            }
        });
        soduko.getView().requestFocusInWindow(); 
    }
}
