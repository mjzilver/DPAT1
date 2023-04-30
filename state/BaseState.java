package state;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import engine.Soduko;
import view.BaseView;

public abstract class BaseState {
    protected Soduko soduko;
    protected KeyAdapter keyAdapter; 

    BaseState(Soduko soduko) {
        this.soduko = soduko;
        BaseView view = soduko.getView();
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
                    soduko.switchState();
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
