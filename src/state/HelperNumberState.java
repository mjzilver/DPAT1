package state;

import board.Board;
import board.Cell;
import board.CellType;
import engine.GUIController;

public class HelperNumberState extends BaseState {
    public HelperNumberState(GUIController guiController) {
        super(guiController);
    }

    @Override
    protected void handleNumber(int number) {
        if (number == 0) {
            return;
        }

        Board board = guiController.getBoardController().getBoard();
        Cell cell = board.getSelectedCell();

        if (cell.getType() == CellType.FINAL || cell.getType() == CellType.GIVEN) {
            return;
        }
        cell.addPossibleValue(number);
        cell.setType(CellType.HELPER);
        board.notifyObservers();
    }
}
