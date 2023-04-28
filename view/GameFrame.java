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
                    // convert key code to integer by subtracting the 0th
                    int number = e.getKeyCode() - KeyEvent.VK_0; 
                    view.handleNumber(number); 
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(); 
                int y = e.getY();
                
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
