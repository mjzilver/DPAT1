package view;
import javax.swing.JFrame;

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
    }
	
	public void setActiveView(){
		this.getContentPane().removeAll();
		this.getContentPane().add(this.view);
		this.pack();
		this.setVisible(true);
		this.repaint();		
	}
}
