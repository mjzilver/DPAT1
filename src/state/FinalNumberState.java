package state;

import board.CellType;
import engine.GUIController;

public class FinalNumberState extends BaseState {
    public FinalNumberState(GUIController guiController) {
        super(guiController);
    }

    @Override
    protected void handleNumber(int number) {
        guiController.getBoardController().getBoard().setSelectedCell(number, CellType.FINAL);
    }
}
