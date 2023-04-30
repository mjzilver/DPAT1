package state;

import board.CellType;
import engine.Soduko;
import view.BaseView;

public class HelperNumberState extends BaseState {
    public HelperNumberState(Soduko soduko) {
        super(soduko);
    }

    @Override
    protected void handleNumber(int number) {
        BaseView view = soduko.getView();
        soduko.getBoard().setCell(view.selectedCellY, view.selectedCellX, number, CellType.HELPER);
    }
}
