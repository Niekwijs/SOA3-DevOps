package pipeline;

public class PipeLine extends CompositeComponent{

    private String pipeLineName;
    private boolean automatic;

    public PipeLine(String pipeLineName, boolean automatic) {
        this.pipeLineName = pipeLineName;
        this.automatic = automatic;
    }

    public String getPipeLineName(){
        return this.pipeLineName;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitPipeline(this);
        super.acceptVisitor(visitor);
    }
}
