import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import board.Board;
import board.BoardFactory;
import board.Cell;
import board.CellType;
import view.BaseView;

public class ViewTest {
    private BaseView view;
    private Board board;
    private int screenWidth;
    private int spacing;

    @Before
    public void setUp() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.createBoard("puzzle.4x4");

        view = new BaseView(board) {
            private static final long serialVersionUID = 1L;

            public void drawCell(Graphics g, int y, int x, Cell cell) {}

            protected void drawDecoratedCell(Graphics g, int y, int x, Cell cell) {}
        };

        try {
            Field field = BaseView.class.getDeclaredField("width");
            field.setAccessible(true);
            screenWidth = (int) field.get(view);

            Field field2 = BaseView.class.getDeclaredField("spacing");
            field2.setAccessible(true);
            spacing = (int) field2.get(view);
        } catch (Exception e) {
            // test failed if exception is thrown
            assertTrue(false);
        }
    }

    @Test
    public void testSelection() {
        assertEquals(board.getCell(0, 0), board.getSelectedCell());

        // select a cell (using spacing so it doesnt click outside of the cell)
        view.handleClick(screenWidth - (spacing * 2), screenWidth - (spacing * 2));
        assertEquals(board.getCell(3, 3), board.getSelectedCell());

        board.setSelectedCell(1, CellType.FINAL);
        assertEquals(1, board.getSelectedCell().getValue());

        board.selectCell(2, 2);
        board.setSelectedCell(2, CellType.GIVEN);
        assertEquals(2, board.getSelectedCell().getValue());
    }

    @Test
    public void testSelectionOutOfBounds() {
        assertEquals(board.getCell(0, 0), board.getSelectedCell());

        // select a cell (using spacing so it doesnt click outside of the cell)
        view.handleClick(screenWidth + 1, screenWidth + 1);
        assertEquals(board.getCell(0, 0), board.getSelectedCell());

        // select a cell (using spacing so it doesnt click outside of the cell)
        view.handleClick(-1, -1);
        assertEquals(board.getCell(0, 0), board.getSelectedCell());
    }
}
