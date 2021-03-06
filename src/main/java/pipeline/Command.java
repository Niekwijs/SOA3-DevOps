package pipeline;

public class Command implements Component{
    private String codeLine;

    public Command(String codeLine) {
        this.codeLine = codeLine;
    }
    public String getCodeLine(){
        return codeLine;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitCommand(this);
    }
}
