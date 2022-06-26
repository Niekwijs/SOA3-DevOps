package pipeline;

public interface Component {
    void acceptVisitor(Visitor visitor);
}
