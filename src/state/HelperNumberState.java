package state;

import board.CellType;
import engine.Sudoku;
import view.BaseView;

public class HelperNumberState extends BaseState {
    public HelperNumberState(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    protected void handleNumber(int number) {
        BaseView view = sudoku.getView();
        sudoku.getBoard().setCell(view.selectedCellY, view.selectedCellX, number, CellType.HELPER);
    }
}
