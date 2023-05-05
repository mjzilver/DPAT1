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
        
        // you cant write a helper number over a final number
        if(sudoku.getBoard().getCell(view.getSelectedCellY(), view.getSelectedCellX()).getType() == CellType.FINAL)
            return;
        sudoku.getBoard().setCell(view.getSelectedCellY(), view.getSelectedCellX(), number, CellType.HELPER);
    }
}
