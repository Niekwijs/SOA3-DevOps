package pipeline;

public class Stage extends CompositeComponent {
    private String stageName;

    public Stage(String stageName) {
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitStage(this);
        super.acceptVisitor(visitor);
    }
}
