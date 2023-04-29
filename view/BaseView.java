package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import board.Board;
import board.Cell;
import board.CellHolder;
import observer.Observer;

public class BaseView extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	public final static int HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8);
	public final static int WIDTH = HEIGHT;
    public final static int RECTSIZE = WIDTH / 10;
    public final static int SPACING = WIDTH / 100;
    public final static int FONTTSIZE = RECTSIZE / 2;

    private Board board;

    private int selectedCellX = -1;
    private int selectedCellY = -1;

	public BaseView(Board board) {
       this.board = board;
       board.attach(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(39, 43, 48));
        ArrayList<CellHolder> rows = board.getRows();
        for (int y = 0; y < rows.size(); y++) {
            ArrayList<Cell> cells = board.getRows().get(y).getCells();
            for (int x = 0; x < cells.size(); x++) {

                int xpos = SPACING + x * (RECTSIZE + SPACING);
                int ypos = SPACING + y * (RECTSIZE + SPACING);

                if(x == selectedCellX && y == selectedCellY)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.BLACK);

                g.fillRect(
                    xpos, 
                    ypos, 
                    RECTSIZE, 
                    RECTSIZE);

                g.setFont(g.getFont().deriveFont(((float)FONTTSIZE)));
                g.setColor(Color.WHITE);

                String text = Integer.toString(cells.get(x).getValue());
                Font font = new Font("Arial", Font.PLAIN, FONTTSIZE);
                FontMetrics metrics = g.getFontMetrics(font);
                int textWidth = metrics.stringWidth(text);
                int textHeight = metrics.getHeight();

                g.drawString(text, xpos + (textWidth), ypos + (textHeight));
            }
        }
	}
	
    public void handleClick(int y, int x) {
        // round it down to the y, x used by the board
        selectedCellX = (int) Math.floor((x - SPACING) / (double)(RECTSIZE + SPACING));
        selectedCellY = (int) Math.floor((y - SPACING) / (double)(RECTSIZE + SPACING));
        repaint();
    }

	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

    public void handleNumber(int number) {
        board.setCell(selectedCellY, selectedCellX, number);
    }

    @Override
    public void update() {
        repaint();
    }
}
