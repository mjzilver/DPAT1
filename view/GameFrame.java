package view;
import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    BaseView view;

	public GameFrame(BaseView view) {
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
				} else if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) {
                    int number = e.getKeyCode() - KeyEvent.VK_0; // convert key code to integer
                    view.handleNumber(number); // pass the number to a function
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(); // get x coordinate of mouse click
                int y = e.getY(); // get y coordinate of mouse click
                
                view.handleClick(y, x);
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
