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
        int xPos = spacing + x * (rectSize + spacing);
        int yPos = spacing + y * (rectSize + spacing);

        if (cell.getType() == CellType.FINAL || cell.getType() == CellType.KNOWN) {
            String text = Integer.toString(cell.getValue());
            g.setFont(g.getFont().deriveFont(((float) fontSize)));
            switch (cell.getType()) {
                case FINAL:
                    g.setColor(Color.darkGray);
                    break;
                case KNOWN:
                    g.setColor(Color.white);
                    break;
                default:
                    break;
            }
            Font font = new Font("Arial", Font.PLAIN, fontSize);
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();

            g.drawString(text, xPos + (textWidth), yPos + (textHeight));
        }
    }

    @Override
    public String getTitle() {
        return super.getTitle() + "Final";
    }
}
