package state;

import board.CellType;
import engine.GUIController;
import view.BaseView;

public class FinalNumberState extends BaseState {
    public FinalNumberState(GUIController guiController) {
        super(guiController);
    }

    @Override
    protected void handleNumber(int number) {
        BaseView view = guiController.getView();
        guiController.getBoardController().getBoard().setCell(view.getSelectedCellY(), view.getSelectedCellX(), number, CellType.FINAL);
    }
}
