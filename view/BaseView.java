package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import board.Board;
import board.Cell;
import board.CellHolder;

public class BaseView extends JPanel {

	private static final long serialVersionUID = 1L;
		
	public final static int WIDTH = 900;
	public final static int HEIGHT = 910;
    public final static int RECTSIZE = 90;
    public final static int SPACING = 10;
    public final static int FONTTSIZE = 50;
    public final static int FONTSPACING = 70;

    private Board board;

    private int selectedCellX = -1;
    private int selectedCellY = -1;

	public BaseView(Board board) {
       this.board = board;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(39, 43, 48));
        ArrayList<CellHolder> rows = this.board.getRows();
        for (int y = 0; y < rows.size(); y++) {
            ArrayList<Cell> cells = this.board.getRows().get(y).getCells();
            for (int x = 0; x < cells.size(); x++) {

                int xpos = x * (RECTSIZE + SPACING);
                int ypos = SPACING + y * + (RECTSIZE + SPACING);

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
                g.drawString(Integer.toString(cells.get(x).getValue()), xpos + FONTSPACING/2, ypos + FONTSPACING);
            }
        }
	}
	
    public void handleClick(int y, int x) {
        this.selectedCellX = (int) Math.floor((x - SPACING) / (double)(RECTSIZE + SPACING));
        this.selectedCellY = (int) Math.floor((y - SPACING) / (double)(RECTSIZE + SPACING));
    }

	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

    public void handleNumber(int number) {
        this.board.getCell(selectedCellY, selectedCellX).setValue(number);
    }
}
