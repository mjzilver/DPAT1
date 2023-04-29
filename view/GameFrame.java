package view;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public GameFrame(BaseView view) {
        super("Sudoku Board");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(view);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
    }
	
	public void setActiveView(BaseView view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(view);
		this.pack();
		this.setVisible(true);
		this.repaint();		
	}
}
