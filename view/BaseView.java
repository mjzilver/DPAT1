package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BaseView extends JPanel {

	private static final long serialVersionUID = 1L;
		
	public final static int WIDTH = 800;
	public final static int HEIGHT = 800;
	
	public BaseView() {
       
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(39, 43, 48));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
}
