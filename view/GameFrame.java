package view;
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private BaseView baseView = new BaseView();

	public GameFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(baseView);
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
		this.getContentPane().add(baseView);
		this.pack();
		this.setVisible(true);
		this.repaint();		
	}
}
