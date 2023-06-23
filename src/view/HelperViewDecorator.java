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
        final int xPos = spacing + x * (rectSize + spacing);
        final int yPos = spacing + y * (rectSize + spacing);

        if (cell.getType() != CellType.EMPTY) {
            g.setColor(Color.white);

            if (cell.getType() == CellType.HELPER) {
                String text = cell.getPossibleValues();
                g.setFont(g.getFont().deriveFont(((float) fontSize / 2)));

                FontMetrics metrics = g.getFontMetrics(g.getFont());
                int textWidth = metrics.stringWidth(text);
                int textHeight = metrics.getHeight();

                // split text in two lines if it is too long
                // then draw both lines
                if (text.length() > 5) {
                    int midpoint = text.length() / 2;
                    String firstLine = text.substring(0, midpoint);
                    String secondLine = text.substring(midpoint);

                    g.drawString(firstLine,
                            xPos + (rectSize / 2) - (textWidth / 4),
                            yPos + (rectSize / 2) + (textHeight / 4) - textHeight);
                    g.drawString(secondLine,
                            xPos + (rectSize / 2) - (textWidth / 4),
                            yPos + (rectSize / 2) + (textHeight / 4));
                } else {
                    g.drawString(text,
                            xPos + (rectSize / 2) - (textWidth / 2),
                            yPos + (rectSize / 2) + (textHeight / 4));
                }
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
