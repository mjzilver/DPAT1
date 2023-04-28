package board;

public class BoardFactory {
    public Board createBoard() {
        return new Board(9, 9);
    }
}
