package pipeline;

public abstract class Component {
    public abstract void acceptVisitor(Visitor visitor);
}
