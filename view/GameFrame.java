package view;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    JPanel view;

	public GameFrame(JPanel view) {
        super("Sudoku Board");

        this.view = view;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(view);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);

        this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) { 
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
				}
            }
        });
	}
	
	public void setActiveView(){
		this.getContentPane().removeAll();
		this.getContentPane().add(this.view);
		this.pack();
		this.setVisible(true);
		this.repaint();		
	}
}
