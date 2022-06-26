package pipeline;

public interface Visitor {
    void visitPipeline(PipeLine pipeLine);

    void visitStage(Stage stage);

    void visitCommand(Command command);
}
