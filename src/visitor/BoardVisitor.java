package visitor;

import java.util.ArrayList;

import board.Board;
import board.CellHolder;

public interface BoardVisitor {
    boolean visit(Board board);
    boolean visit(ArrayList<CellHolder> list);
    boolean visit(CellHolder cellHolder);
}