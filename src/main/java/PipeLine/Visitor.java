package pipeline;

public abstract class Visitor {
    public abstract void visitPipeline(PipeLine pipeLine);

    public abstract void visitStage(Stage stage);

    public abstract void visitCommand(Command command);
}
