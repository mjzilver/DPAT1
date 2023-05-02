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

    public final static int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8);
    public final static int WIDTH = HEIGHT;
    public final static int RECTSIZE = WIDTH / 10;
    public final static int SPACING = WIDTH / 100;
    public final static int FONTTSIZE = RECTSIZE / 2;
    // for some reason a mouseclick is offset
    public final static int MOUSEOFFSET = 20; 

    private Board board;

    public String title = "Sodoku";
    public int selectedCellX = -1;
    public int selectedCellY = -1;

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
                Cell currentCell = cells.get(x);
                drawCell(g, y, x, currentCell);
            }
        }
    }

    protected void drawCell(Graphics g, int y, int x, Cell cell) {
        int xpos = SPACING + x * (RECTSIZE + SPACING);
        int ypos = SPACING + y * (RECTSIZE + SPACING);

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
                RECTSIZE,
                RECTSIZE);

        if (x == selectedCellX && y == selectedCellY) {
            g.setColor(Color.blue);
            g.drawRoundRect(
                    xpos,
                    ypos,
                    RECTSIZE,
                    RECTSIZE,
                    10,
                    10);
        }

        drawDecoratedCell(g, y, x, cell);
    }

    protected abstract void drawDecoratedCell(Graphics g, int y, int x, Cell cell);

    public void handleClick(int y, int x) {
        y += MOUSEOFFSET; // this is needed
        Point mousePoint = new Point(x, y);
        SwingUtilities.convertPointFromScreen(mousePoint, this);
        // round it down to the y, x used by the board
        selectedCellX = (int) Math.floor((mousePoint.x - SPACING) / (double) (RECTSIZE + SPACING));
        selectedCellY = (int) Math.floor((mousePoint.y - SPACING) / (double) (RECTSIZE + SPACING));
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
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
