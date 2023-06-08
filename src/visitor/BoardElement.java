package visitor;

public interface BoardElement {
    void accept(BoardVisitor visitor);
}