package state;

import engine.Soduko;
import view.BaseView;

public class FinalNumberState extends BaseState {
    public FinalNumberState(Soduko soduko) {
        super(soduko);
    }

    @Override
    protected void handleNumber(int number) {
        BaseView view = soduko.getView();
        soduko.getBoard().setCell(view.selectedCellY, view.selectedCellX, number, true);
    }
}
