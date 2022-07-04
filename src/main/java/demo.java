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
import report.ExportType;
import report.ReportBuilder;
import sprint.Sprint;
import sprint.SprintType;

import java.util.ArrayList;
import java.util.Date;

public class demo {

    public static void main(String[] args) throws ChangeSprintStateException {
//        demoState();
//        demoObserver();
//        demoComposite();
        demoStrategy();
//        demoAdapter();
//        demoFactory();
    }

    public static void demoState() {
        BacklogItem backlogItem = new BacklogItem("test item", 1, 2, 3);
        Developer developer = new Developer("testDev", 1, "test@mial.com", "01111111", "tester45");

        try {
            backlogItem.getState().changeToReadyForTestingState(developer);
        } catch (Exception e) {
            System.out.println("This is not allowed in the current state!");
        }

        try {
            backlogItem.getState().changeToDoingState();
        } catch (Exception e) {
            System.out.println("This should not get called!");
        }

        System.out.println("Current state: " + backlogItem.getState().getClass().getSimpleName());

        try {
            backlogItem.getState().changeToReadyForTestingState(developer);
        } catch (Exception e) {
            System.out.println("This should not get called!");
        }

        System.out.println("Final state: " + backlogItem.getState().getClass().getSimpleName());
    }

    public static void demoObserver() throws ChangeSprintStateException {
        ProjectFactory projectFactory = new ProjectFactory();
        IProject project = projectFactory.getProject("scrum", "1st Scrum project");

        Account scrumMaster = new ScrumMaster("testScrumMaster", 1, "test@email.com", "0612345678", "testUser");
        Account productOwner = new ProductOwner("testProductOwner", 2, "test@email.com", "0612345678", "testUser");

        Sprint sprint = new Sprint(SprintType.RELEASE, "Sprint 1", scrumMaster, productOwner, project, new Date(), new Date());

        INotifier notifier = new SlackNotify();
        Subscriber sub = new NotificationService(notifier);
        sprint.subscribe(scrumMaster, sub);

        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleaseCancelledState();
    }

    public static void demoComposite() {
        // Pipeline extends CompositeComponent
        PipeLine pipeLine = new PipeLine("pipeline test", false);

        // Stage also extends CompositeComponent
        Stage stage1 = new Stage("Stage 1");
        // Command extends component
        Command command1 = new Command("build");

        Stage stage2 = new Stage("Stage 2");
        Command command2 = new Command("test");

        // Add the stage to the pipeline
        pipeLine.addComponent(stage1);
        pipeLine.addComponent(stage2);

        // Add the commands to the stage, previously added to the pipeline
        stage1.addComponent(command1);
        stage2.addComponent(command2);

        // A visitor can print all the parts in the pipeline
        PrintVisitor printVisitor = new PrintVisitor();
        pipeLine.acceptVisitor(printVisitor);
    }

    public static void demoStrategy() {
        ReportBuilder reportBuilder = new ReportBuilder();

        reportBuilder.setExportType(ExportType.PDF);

        ArrayList<String> contents = new ArrayList<>();
        contents.add("This is a demo of the strategy pattern!");

        reportBuilder.addHeader("Company", "Sprint 1", 1, new Date());
        reportBuilder.addContent(contents);
        reportBuilder.addFooter("Address", "Company", "0612345678");
        reportBuilder.getReport();
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
