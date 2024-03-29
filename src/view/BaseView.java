package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import board.Board;
import board.Cell;
import board.CellType;
import observer.Observer;

public abstract class BaseView extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;

    protected final int height;
    protected final int width;
    protected final int rectSize;
    protected final int spacing;
    protected final int fontSize;
    // for some reason a mouseclick is offset by 20 pixels
    protected final static int mouseOffset = 20;

    protected final static int[][] colors = {
            { 0, 0, 255 }, // blue
            { 75, 0, 130 }, // indigo
            { 238, 130, 238 }, // violet
            { 0, 128, 128 }, // teal
            { 255, 69, 0 }, // Orange Red
            { 0, 128, 0 }, // Dark Green
            { 165, 42, 42 }, // Brown
            { 70, 130, 180 }, // Steel Blue
            { 128, 0, 0 }, // Maroon
            { 192, 0, 192 } // Purple
    };

    private Board board;

    public BaseView(Board board) {
        this.board = board;
        board.attach(this);

        // max 70% of the screenheight just fits nicely
        int maxHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8);

        this.rectSize = maxHeight / (board.getHeight() + 1);
        this.spacing = maxHeight / ((board.getHeight() + 1) * 10);
        this.height = rectSize * (board.getHeight()) + spacing * (board.getHeight() + 1);
        this.width = rectSize * (board.getWidth()) + spacing * (board.getWidth() + 1);
        this.fontSize = rectSize / 2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Cell currentCell = board.getCell(y, x);
                if (currentCell.getType() != CellType.INACTIVE) {
                    drawCell(g, y, x, currentCell);
                }
            }
        }
    }

    protected void drawCell(Graphics g, int y, int x, Cell cell) {
        int xpos = spacing + x * (rectSize + spacing);
        int ypos = spacing + y * (rectSize + spacing);

        g.setColor(new Color(
                colors[board.getBoxIndex(y, x) % 10][0],
                colors[board.getBoxIndex(y, x) % 10][1],
                colors[board.getBoxIndex(y, x) % 10][2]));

        g.fillRect(
                xpos,
                ypos,
                rectSize,
                rectSize);

        if (cell == board.getSelectedCell()) {
            g.setColor(Color.black);
            g.drawRoundRect(
                    xpos + (rectSize / 10),
                    ypos + (rectSize / 10),
                    (int) (rectSize * 0.8),
                    (int) (rectSize * 0.8),
                    (int) (rectSize * 0.8),
                    (int) (rectSize * 0.8));
        }

        drawDecoratedCell(g, y, x, cell);

        if (cell.getType() == CellType.FINAL) {
            switch (cell.getStatus()) {
                case CORRECT:
                    drawCorrect(g, ypos, xpos);
                    break;
                case WRONG:
                    drawWrong(g, ypos, xpos);
                    break;
                default:
                    break;
            }
        }
    }

    private void drawCorrect(Graphics g, int ypos, int xpos) {
        // draw checkmark
        g.setColor(Color.GREEN);

        g.drawLine(
                xpos + (rectSize / 10),
                ypos + (rectSize / 2),
                xpos + (rectSize / 3),
                ypos + (int) (rectSize * 0.8));
        g.drawLine(
                xpos + (rectSize / 3),
                ypos + (int) (rectSize * 0.8),
                xpos + (int) (rectSize * 0.9),
                ypos + (rectSize / 10));
    }

    private void drawWrong(Graphics g, int ypos, int xpos) {
        g.setColor(Color.RED);
        // draw an X that covers the number
        g.drawLine(
                xpos,
                ypos,
                xpos + rectSize,
                ypos + rectSize);
        g.drawLine(
                xpos + rectSize,
                ypos,
                xpos,
                ypos + rectSize);

    }

    protected abstract void drawDecoratedCell(Graphics g, int y, int x, Cell cell);

    public void handleClick(int y, int x) {
        y -= mouseOffset; // this is needed

        // round it down to the y, x used by the board
        int cellX = (int) Math.floor((x - spacing) / (double) (rectSize + spacing));
        int cellY = (int) Math.floor((y - spacing) / (double) (rectSize + spacing));

        if (cellX < 0 || cellX >= board.getWidth() || cellY < 0 || cellY >= board.getHeight()) {
            return;
        }

        board.selectCell(cellY, cellX);

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
