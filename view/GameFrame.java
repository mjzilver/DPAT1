package view;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameFrame(BaseView view) {
        super(view.title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(view);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addKeyListener(this.keyAdapter());
		this.pack();
		this.setVisible(true);
    }
	
	public void setActiveView(BaseView view) {
		this.setTitle(view.title);
		this.getContentPane().removeAll();
		this.getContentPane().add(view);
		this.pack();
		this.setVisible(true);
		this.repaint();		
	}

	public void addTopView(JPanel view) {
		this.getContentPane().add(view, 0);
		this.pack();
		this.setVisible(true);
		this.repaint();
	}
	

	public void addView(JPanel view) {
		this.getContentPane().add(view);
		this.pack();
		this.setVisible(true);
		this.repaint();
	}

    public void removeView(BaseView view) {
		this.getContentPane().remove(view);
		this.pack();
		this.setVisible(true);
		this.repaint();
    }

	public KeyAdapter keyAdapter() {
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				for(Component component : getContentPane().getComponents()) {
					if(component instanceof BaseView) {
						component.dispatchEvent(e);
					}
				}
			}
		};
	}
}
