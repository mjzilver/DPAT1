package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameFrame(BaseView view) {
		super(view.getTitle());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(view);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.pack();
		this.setVisible(true);
	}

	public void switchView(BaseView oldView, BaseView newView) {
		this.getContentPane().remove(oldView);
		this.getContentPane().add(newView, 0);
		this.setTitle(newView.getTitle());
		this.pack();
		this.setVisible(true);
		this.repaint();
		newView.requestFocusInWindow();
	}

	public void addView(JPanel view) {
		this.getContentPane().add(view);
		this.pack();
		this.setVisible(true);
		this.repaint();
	}
}
