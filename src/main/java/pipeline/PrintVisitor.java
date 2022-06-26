package pipeline;

public class PrintVisitor implements Visitor {

    @Override
    public void visitPipeline(PipeLine pipeLine) {
        System.out.println("~~~~~~~~~" + pipeLine.getPipeLineName() + "~~~~~~~~~");
    }

    @Override
    public void visitStage(Stage stage) {
        System.out.println("==========" + stage.getStageName()+ "==========");

    }

    @Override
    public void visitCommand(Command command) {
        System.out.println("execute " + command.getCodeLine()+ ".....");
    }
}
