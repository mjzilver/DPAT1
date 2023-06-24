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
        Cell selectedCell = board.getSelectedCell();

        if (selectedCell.getType() == CellType.FINAL || selectedCell.getType() == CellType.GIVEN) {
            return;
        }
        selectedCell.addPossibleValue(number);
        selectedCell.setType(CellType.HELPER);
        board.notifyObservers();
    }
}
