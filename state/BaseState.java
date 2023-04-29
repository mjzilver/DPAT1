package state;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import engine.Soduko;
import view.BaseView;

public class BaseState {
    BaseState(Soduko soduko) {
        BaseView view = soduko.getView();
        System.out.println(view);
        view.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) { 
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
				} else if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_R) {
                    soduko.switchState();
                }
            }
        });
        view.requestFocusInWindow(); 
    }
}
