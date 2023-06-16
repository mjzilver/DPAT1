package board.composite;

import visitor.BoardVisitor;

public interface BoardComponent {
    public void accept(BoardVisitor visitor);
    public void uncheck();
    public void debugPrint();
}