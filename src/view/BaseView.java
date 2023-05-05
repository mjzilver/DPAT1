package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import board.Board;
import board.Cell;
import board.CellHolder;
import observer.Observer;

public abstract class BaseView extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;

    protected final int height;
    protected final int width;
    protected final int rectSize;
    protected final int spacing;
    protected final int fontSize;
    // for some reason a mouseclick is offset by 20 pixels
    protected int mouseOffset = 20; 

    private Board board;

    public int selectedCellX = 0;
    public int selectedCellY = 0;

    public BaseView(Board board) {
        this.board = board;
        board.attach(this);

        // max 70% of the screeneheight just fixs nicely
        int maxHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);

        this.rectSize = maxHeight / (board.getHeight() + 1);	
        this.spacing = maxHeight / ((board.getHeight() + 1) * 10);	
        this.height = rectSize * (board.getHeight() + 1);
        this.width = height;
        this.fontSize = rectSize / 2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(39, 43, 48));
        ArrayList<CellHolder> rows = board.getRows();
        for (int y = 0; y < rows.size(); y++) {
            ArrayList<Cell> cells = board.getRows().get(y).getCells();
            for (int x = 0; x < cells.size(); x++) {
                Cell currentCell = cells.get(x);
                drawCell(g, y, x, currentCell);
            }
        }
    }

    protected void drawCell(Graphics g, int y, int x, Cell cell) {
        int xpos = spacing + x * (rectSize + spacing);
        int ypos = spacing + y * (rectSize + spacing);

        switch (cell.getStatus()) {
            case CORRECT:
                g.setColor(Color.green);
                break;
            case UNCHECKED:
                g.setColor(Color.black);
                break;
            case WRONG:
                g.setColor(Color.red);
                break;
        }

        g.fillRect(
                xpos,
                ypos,
                rectSize,
                rectSize);

        if (x == selectedCellX && y == selectedCellY) {
            g.setColor(Color.blue);
            g.drawRoundRect(
                    xpos,
                    ypos,
                    rectSize,
                    rectSize,
                    10,
                    10);
        }

        drawDecoratedCell(g, y, x, cell);
    }

    protected abstract void drawDecoratedCell(Graphics g, int y, int x, Cell cell);

    public void handleClick(int y, int x) {
        y += mouseOffset; // this is needed
        Point mousePoint = new Point(x, y);
        SwingUtilities.convertPointFromScreen(mousePoint, this);
        // round it down to the y, x used by the src.board
        selectedCellX = (int) Math.floor((mousePoint.x - spacing) / (double) (rectSize + spacing));
        selectedCellY = (int) Math.floor((mousePoint.y - spacing) / (double) (rectSize + spacing));
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public void update() {
        repaint();
    }

    public void deconstruct() {
        KeyListener[] keyListeners = this.getKeyListeners();
        for (KeyListener kl : keyListeners) {
            this.removeKeyListener(kl);
        }
    }

    public String getTitle() {
        return "Sodoku - ";
    }
}
