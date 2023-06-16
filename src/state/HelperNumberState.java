package state;

import board.Cell;
import board.CellType;
import engine.GUIController;
import view.BaseView;

public class HelperNumberState extends BaseState {
    public HelperNumberState(GUIController guiController) {
        super(guiController);
    }

    @Override
    protected void handleNumber(int number) {
        if (number == 0) {
            return;
        }

        BaseView view = guiController.getView();
        Cell cell = guiController.getBoardController().getBoard().getCell(view.getSelectedCellY(), view.getSelectedCellX());

        if (cell.getType() == CellType.FINAL || cell.getType() == CellType.GIVEN) {
            return;
        }
        cell.addPossibleValue(number);
        cell.setType(CellType.HELPER);
        guiController.getBoardController().getBoard().notifyObservers();
    }
}
