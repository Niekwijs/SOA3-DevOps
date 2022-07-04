import account.Account;
import account.Developer;
import account.ProductOwner;
import account.ScrumMaster;
import backlog.BacklogItem;
import exceptions.ChangeSprintStateException;
import notification.*;
import pipeline.Command;
import pipeline.PipeLine;
import pipeline.PrintVisitor;
import pipeline.Stage;
import project.IProject;
import project.ProjectFactory;
import sprint.Sprint;
import sprint.SprintType;
import java.util.Date;

public class demo {

    public static void main(String[] args) throws ChangeSprintStateException {
//        demoState();
//        demoObserver();
        demoComposite();
//        demoStrategy();
//        demoAdapter();
//        demoFactory();
    }

    public static void demoState() {
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Developer developer = new Developer("testDev", 1,"test@mial.com","01111111","tester45" );

        System.out.println("[+] created  new backlog item"+ backlogItem.getDescription());
        System.out.println("[+] status backlog item"+ backlogItem.getState().toString());

        System.out.println("[+] trying to change status to ready for testing (should not work!)");
        try {
            backlogItem.getState().changeToReadyForTestingState(developer);
        } catch (Exception e) {
            System.out.println("[+] this was not allowed in the current state");
        }

        try {
            backlogItem.getState().changeToDoingState();
        } catch (Exception e) {
            System.out.println("[+] this should not get called!");
        }
        System.out.println("[+] changed to doing state");
        System.out.println(backlogItem.getState().toString());

        System.out.println("[+] lets try to change the state again!");
        try {
            backlogItem.getState().changeToReadyForTestingState(developer);

        } catch (Exception e) {
            System.out.println("[+] This exception should not be thrown");
        }

        System.out.println("[+] The state of the backlogitem should be ready for testing state");
        System.out.println(backlogItem.getState().toString());
    }

    public static void demoObserver() throws ChangeSprintStateException {
        ProjectFactory projectFactory = new ProjectFactory();
        IProject project = projectFactory.getProject("scrum", "1st Scrum project");

        Account scrumMaster = new ScrumMaster("testScrumMaster", 1, "test@email.com", "0612345678", "testUser");
        Account productOwner = new ProductOwner("testProductOwner", 2, "test@email.com", "0612345678", "testUser");

        Sprint sprint = new Sprint(SprintType.RELEASE,"Sprint 1", scrumMaster, productOwner, project, new Date(), new Date());

        INotifier notifier = new SlackNotify();
        Subscriber sub = new NotificationService(notifier);
        sprint.subscribe(scrumMaster, sub);

        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleaseCancelledState();
    }

    public static void demoComposite() {
        System.out.println("[+] lets create a pipeline which extends CompositeComponent");
        PipeLine pipeLine = new PipeLine("pipeline test", false);
        System.out.println("[+] pipeline created with the name "+ pipeLine.getPipeLineName());
        System.out.println("[+] this CompositeComponent holds a list with Components aka parts of the whole");

        System.out.println("[+] Lets create a new stage which also extends CompositeComponent");
        Stage stage1 = new Stage("source");
        System.out.println("[+] created a new Stage with name" + stage1.getStageName());

        System.out.println("[+] lets also create a command which extends component");
        System.out.println("[+] a component can be seen as a leaf from a tree and cant contain any children");
        Command command1 = new Command("source");
        System.out.println("[+] created a new command with the codeline "+ command1.getCodeLine());

        System.out.println("[+] this is fun and all but what is the point?");
        System.out.println("[+] it lets us combine the parts of the pipeline, sort of like a tree.");

        System.out.println("[+] lets add the stage to our pipeline");
        pipeLine.addComponent(stage1);
        System.out.println("[+] we successfully added stage" + pipeLine.getComponent(0).toString());

        System.out.println("[+] now lets add a command to our pipeline so something gets executed");
        stage1.addComponent(command1);
        System.out.println("[+] this command has been added to our stage "+ stage1.getComponent(0).toString());

        System.out.println("[+] lets create another command ");
        Command command2 = new Command("load package.json");
        System.out.println("[+] created a new command with codeline "+ command2.getCodeLine());

        System.out.println("[+] the composite pattern lets us add this command in the same array where stage has been added");
        pipeLine.addComponent(command2);

        System.out.println("[+] lets check if our command has been added next to the stage we added earlier ");
        System.out.println("[" + pipeLine.getComponent(0) + "," + pipeLine.getComponent(1) + "]");

        System.out.println("[+] now lets create a vistor to print all the names of the parts in our pipeline");
        PrintVisitor printVisitor = new PrintVisitor();

        System.out.println("[+] now lets pass the visitor to all components whom extend compositeComponent and component");
        pipeLine.acceptVisitor(printVisitor);
        System.out.println("[+] this visitor pattern can be used to add methods to objects and define object specific logic ");
    }

    public static void demoStrategy() {

    }

    public static void demoAdapter() {

    }

    public static void demoFactory() {
        ProjectFactory projectFactory = new ProjectFactory();

        IProject scrumProject = projectFactory.getProject("scrum", "1st Scrum project");
        IProject kanbanProject = projectFactory.getProject("kanban", "1st Kanban project");

        System.out.println("Scrum: " + scrumProject.getName() + ", type: " + scrumProject.getClass().getSimpleName());
        System.out.println("Kanban: " + kanbanProject.getName() + ", type: " + kanbanProject.getClass().getSimpleName());
    }

}
