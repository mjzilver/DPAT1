package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import board.Board;
import board.Cell;
import board.CellType;

public class FinalViewDecorator extends BaseView {
    public FinalViewDecorator(Board board) {
        super(board);
    }

    @Override
    public void drawDecoratedCell(Graphics g, int y, int x, Cell cell) {
        int xpos = SPACING + x * (RECTSIZE + SPACING);
        int ypos = SPACING + y * (RECTSIZE + SPACING);

        if (cell.getType() == CellType.FINAL) {
            String text = Integer.toString(cell.getValue());
            g.setFont(g.getFont().deriveFont(((float) FONTTSIZE)));
            switch (cell.getType()) {
                case FINAL:
                    g.setColor(Color.lightGray);
                    break;
                case KNOWN:
                    g.setColor(Color.white);
                    break;
                default:
                    break;
            }
            Font font = new Font("Arial", Font.PLAIN, FONTTSIZE);
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();

            g.drawString(text, xpos + (textWidth), ypos + (textHeight));
        }
    }
}