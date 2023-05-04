package state;

import board.CellType;
import engine.Sudoku;
import view.BaseView;

public class FinalNumberState extends BaseState {
    public FinalNumberState(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    protected void handleNumber(int number) {
        BaseView view = sudoku.getView();
        sudoku.getBoard().setCell(view.selectedCellY, view.selectedCellX, number, CellType.FINAL);
    }
}
