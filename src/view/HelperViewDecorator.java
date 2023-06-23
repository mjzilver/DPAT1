package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import board.Board;
import board.Cell;
import board.CellType;

public class HelperViewDecorator extends BaseView {
    public HelperViewDecorator(Board board) {
        super(board);
    }

    @Override
    public void drawDecoratedCell(Graphics g, int y, int x, Cell cell) {
        int xPos = spacing + x * (rectSize + spacing);
        int yPos = spacing + y * (rectSize + spacing);

        if (cell.getType() != CellType.EMPTY) {
            g.setColor(Color.white);

            if (cell.getType() == CellType.HELPER) {
                String text = cell.getPossibleValues();
                g.setFont(g.getFont().deriveFont(((float) fontSize / 4)));

                Font font = new Font("Arial", Font.ITALIC, (fontSize / 4));
                FontMetrics metrics = g.getFontMetrics(font);
                int textWidth = metrics.stringWidth(text);
                int textHeight = metrics.getHeight();
                g.drawString(text,
                        xPos + (rectSize / 2) - (textWidth / 2),
                        yPos + (rectSize / 5) + (textHeight / 4));
            } else {
                String text = Integer.toString(cell.getValue());
                g.setFont(g.getFont().deriveFont(((float) fontSize)));

                Font font = new Font("Arial", Font.PLAIN, fontSize);
                FontMetrics metrics = g.getFontMetrics(font);
                int textWidth = metrics.stringWidth(text);
                int textHeight = metrics.getHeight();
                g.drawString(text,
                        xPos + (rectSize / 2) - (textWidth / 2),
                        yPos + (rectSize / 2) + (textHeight / 4));
            }
        }
    }

    @Override
    public String getTitle() {
        return super.getTitle() + "Helper";
    }
}
