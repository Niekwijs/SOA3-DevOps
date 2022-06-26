package pipeline;

import notification.Publisher;
import notification.Subscriber;
import com.google.gson.Gson;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

public class PipeLineManager implements Publisher {

    private List<PipeLine> pipeLines;

    public PipeLineManager() {
        this.pipeLines = new ArrayList<PipeLine>();
        this.pipeLines.add(generateBasePipeLine("DefaultDevPipeLine"));
        this.pipeLines.add(generateDefaultDeployPipeLine());
    }

    public PipeLine getPipeline(int index) {
        return this.pipeLines.get(index);
    }

    public void addNewPipeline(PipeLine pipeLine) {
        this.pipeLines.add(pipeLine);
    }

    public void executePipeLineByName(String name) throws InterruptedException {
        Thread.sleep(2000);
        for(PipeLine p : pipeLines){
            if(p.getPipeLineName().equals(name)){
                p.acceptVisitor(new PrintVisitor());
                break;
            }
        }
    }

    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    private PipeLine generateBasePipeLine(String name){
        PipeLine pipeLine = new PipeLine(name, false);

        Stage stage1 = new Stage("source");
        Command command1 = new Command("source");
        Stage stage2 = new Stage("build");
        Command command2 = new Command("build");
        Stage stage3 = new Stage("test");
        Command command3 = new Command("tests");
        Stage stage4 = new Stage("analyse");
        Command command4 = new Command("analyse");

        pipeLine.addComponent(stage1);
        stage1.addComponent(command1);
        pipeLine.addComponent(stage2);
        stage2.addComponent(command2);
        pipeLine.addComponent(stage3);
        stage3.addComponent(command3);
        pipeLine.addComponent(stage4);
        stage4.addComponent(command4);

        return pipeLine;
    }

    private PipeLine generateDefaultDeployPipeLine(){
        PipeLine pipeLine = generateBasePipeLine("DefaultDeployPipeLine");
        Stage stage5 = new Stage("deploy");
        Command command5 = new Command("execute deploy");

        pipeLine.addComponent(stage5);
        stage5.addComponent(command5);
        return pipeLine;
    }
    public void subscribe(Subscriber s) {
        this.subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        this.subscribers.remove(s);
    }

    public void notifySubscribers(String message) {
        for(Subscriber s : subscribers){
            s.update(message);
        }
    }
}
