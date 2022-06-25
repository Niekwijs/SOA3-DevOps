package sprint;

import Backlog.Backlog;
import account.Account;
import account.ProductOwner;
import account.ScrumMaster;
import notification.*;
import pipeline.PipeLine;
import project.ScrumProject;
import report.Report;
import sprint.states.*;
import exceptions.ChangeSprintStateException;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import nl.altindag.console.ConsoleCaptor;

import static org.testng.AssertJUnit.*;

public class SprintTests {

    SprintType release = SprintType.release;
    SprintType review = SprintType.review;
    Backlog backlog = new Backlog();
    Account scrumMaster = new ScrumMaster("testScrumMaster", 1, "test@email.com", "0612345678", "testUser");
    Account productOwner = new ProductOwner("testProductOwner", 2, "test@email.com", "0612345678", "testUser");
    ArrayList<Account> devs = new ArrayList<Account>();
    ArrayList<Account> testers = new ArrayList<Account>();
    ScrumProject project = new ScrumProject("Project 1");
    Date date = new Date();
    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Test
    public void T14_15_1_sprint_can_be_created_with_review_type(){
        // Arrange
        Sprint reviewSprint = new Sprint(review,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        ISprintState state = reviewSprint.getState();

        // Assert
        assertEquals(state.getClass(), InitialState.class);
    }

    @Test
    public void T14_15_2_sprint_can_be_created_with_release_type(){
        // Arrange
        Sprint reviewSprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        ISprintState state = reviewSprint.getState();

        // Assert
        assertEquals(state.getClass(), InitialState.class);
    }

    @Test(expectedExceptions = ChangeSprintStateException.class)
    public void T16_1_sprint_state_can_be_altered_correctly_to_reviewed() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        Report report = new Report();
        sprint.addReport(report);

        // Act
        ISprintState initialState = sprint.getState();

        sprint.getState().changeToInProgressState();
        ISprintState inProgressState = sprint.getState();

        sprint.getState().changeToFinishedState();
        ISprintState finishedState = sprint.getState();

        sprint.getState().changeToReviewedState();
        ISprintState reviewedState = sprint.getState();

        // Assert
        assertEquals(initialState.getClass(), InitialState.class);
        assertEquals(inProgressState.getClass(), InProgressState.class);
        assertEquals(finishedState.getClass(), FinishedState.class);
        assertEquals(reviewedState.getClass(), ReviewedState.class);
    }

    @Test
    public void T16_2_sprint_state_can_be_altered_correctly_to_releaseError_and_releaseCancelled() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        ISprintState initialState = sprint.getState();

        sprint.getState().changeToInProgressState();
        ISprintState inProgressState = sprint.getState();

        sprint.getState().changeToFinishedState();
        ISprintState finishedState = sprint.getState();

        sprint.getState().changeToReleasingState();
        ISprintState releasingState = sprint.getState();

        sprint.getState().changeToReleaseErrorState();
        ISprintState releasingErrorState = sprint.getState();

        sprint.getState().changeToReleaseCancelledState();
        ISprintState releaseCancelledState = sprint.getState();

        // Assert
        assertEquals(initialState.getClass(), InitialState.class);
        assertEquals(inProgressState.getClass(), InProgressState.class);
        assertEquals(finishedState.getClass(), FinishedState.class);
        assertEquals(releasingState.getClass(), ReleasingState.class);
        assertEquals(releasingErrorState.getClass(), ReleaseErrorState.class);
        assertEquals(releaseCancelledState.getClass(), ReleaseCancelledState.class);
    }

    @Test
    public void T16_3_sprint_state_can_be_altered_correctly_to_releaseSuccess() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        ISprintState initialState = sprint.getState();

        sprint.getState().changeToInProgressState();
        ISprintState inProgressState = sprint.getState();

        sprint.getState().changeToFinishedState();
        ISprintState finishedState = sprint.getState();

        sprint.getState().changeToReleasingState();
        ISprintState releasingState = sprint.getState();

        sprint.getState().changeToReleaseSuccessState();
        ISprintState releaseSuccessState = sprint.getState();

        // Assert
        assertEquals(initialState.getClass(), InitialState.class);
        assertEquals(inProgressState.getClass(), InProgressState.class);
        assertEquals(finishedState.getClass(), FinishedState.class);
        assertEquals(releasingState.getClass(), ReleasingState.class);
        assertEquals(releaseSuccessState.getClass(), ReleaseSuccessState.class);
    }

    @Test
    public void T17_1_sprint_data_can_be_altered_in_initial_state(){
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        Date newDate = new Date();

        // Act
        ISprintState state = sprint.getState();
        String initialName = sprint.getName();
        Date initialStartTime = sprint.getStartTime();
        Date initialEndTime = sprint.getEndTime();

        sprint.setName("New name");
        sprint.setStartTime(newDate);
        sprint.setEndTime(newDate);

        // Assert
        assertEquals(state.getClass(), InitialState.class);
        assertEquals(initialName, "Sprint 1");
        assertEquals(initialStartTime, date);
        assertEquals(initialEndTime, date);
        assertEquals(sprint.getName(), "New name");
        assertEquals(sprint.getStartTime(), newDate);
        assertEquals(sprint.getEndTime(), newDate);
    }

    @Test
    public void T17_2_sprint_data_can_not_be_altered_in_inProgress_state() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        Date newDate = new Date();

        // Act
        sprint.getState().changeToInProgressState();
        ISprintState state = sprint.getState();
        String initialName = sprint.getName();
        Date initialStartTime = sprint.getStartTime();
        Date initialEndTime = sprint.getEndTime();

        sprint.setName("New name");
        sprint.setStartTime(newDate);
        sprint.setEndTime(newDate);

        // Assert
        assertEquals(state.getClass(), InProgressState.class);
        assertEquals(initialName, "Sprint 1");
        assertEquals(initialStartTime, date);
        assertEquals(initialEndTime, date);
        assertEquals(sprint.getName(), "Sprint 1");
        assertEquals(sprint.getStartTime(), date);
        assertEquals(sprint.getEndTime(), date);
    }

    @Test
    public void T18_19_1_sprint_release_cancelled_sends_notification() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        INotifier notifier = new SlackNotify();
        Subscriber sub = new NotificationService(notifier);
        sprint.subscribe(scrumMaster, sub);
        sprint.subscribe(productOwner, sub);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseErrorState();
        sprint.getState().changeToReleaseCancelledState();

        // Assert
        assertTrue(os.toString().contains("Sent Slack message: Sprint release has been cancelled"));

        consoleCaptor.clearOutput();
    }

    @Test
    public void T21_1_add_development_pipeline_to_sprint(){
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        PipeLine pipeLine = new PipeLine("DefaultDevPipeLine", false);

        // Act
        sprint.addPipeline(pipeLine);

        // Assert
        assertEquals(pipeLine.getPipeLineName(), sprint.getPipeline().getPipeLineName());
    }

    @Test
    public void T21_2_add_new_development_pipeline_to_sprint(){
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        PipeLine pipeLine1 = new PipeLine("DefaultDevPipeLine0", false);
        PipeLine pipeLine2 = new PipeLine("DefaultDevPipeLine", false);

        // Act
        sprint.addPipeline(pipeLine1);
        sprint.addPipeline(pipeLine2);

        // Assert
        assertEquals(pipeLine2.getPipeLineName(), sprint.getPipeline().getPipeLineName());
    }

    @Test
    public void T23_1_release_sprint_starts_development_pipeline() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseSuccessState();

        // Assert
        boolean status1 = consoleCaptor.getStandardOutput().contains("execute source.....");
        boolean status2 = consoleCaptor.getStandardOutput().contains("execute build.....");
        boolean status3 = consoleCaptor.getStandardOutput().contains("execute tests.....");
        boolean status4 = consoleCaptor.getStandardOutput().contains("execute analyse.....");

        assertTrue(status1);
        assertTrue(status2);
        assertTrue(status3);
        assertTrue(status4);

        consoleCaptor.clearOutput();
    }

    @Test
    public void T23_2_release_sprint_starts_development_pipeline_manually() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        PipeLine pipeLine = new PipeLine("Pipeline", false);
        sprint.addPipeline(pipeLine);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();

        assert(consoleCaptor.getStandardOutput()).isEmpty();

        sprint.getPipeLineManager().executePipeLineByName("Pipeline");
        sprint.getState().changeToReleaseSuccessState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("execute source.....");
        assert(consoleCaptor.getStandardOutput()).contains("execute build.....");
        assert(consoleCaptor.getStandardOutput()).contains("execute tests.....");
        assert(consoleCaptor.getStandardOutput()).contains("execute analyse.....");
        consoleCaptor.clearOutput();
    }

    @Test(expectedExceptions = ChangeSprintStateException.class)
    public void T23_3_review_sprint_cannot_start_development_pipeline() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(review,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        PipeLine pipeLine = new PipeLine("Pipeline", false);
        sprint.addPipeline(pipeLine);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Can't release a review sprint");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T24_25_1_release_sprint_success_sends_notifications() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        INotifier notifier = new SlackNotify();
        Subscriber sub = new NotificationService(notifier);
        sprint.subscribe(scrumMaster, sub);
        sprint.subscribe(productOwner, sub);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseSuccessState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Sent Slack message: The sprint pipeline is executed successfully");
        consoleCaptor.clearOutput();
    }

    @Test(expectedExceptions = ChangeSprintStateException.class)
    public void T26_1_sprint_is_closed_after_development_pipeline_passes() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseSuccessState();
        sprint.getState().changeToReleaseSuccessState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Release success is a final state!");
        consoleCaptor.clearOutput();
    }

    @Test(expectedExceptions = ChangeSprintStateException.class)
    public void T26_2_sprint_is_closed_before_development_pipeline_passes() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleaseSuccessState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Can't change from finished to release success!");
        consoleCaptor.clearOutput();
    }

    @Test(expectedExceptions = ChangeSprintStateException.class)
    public void T26_3_sprint_is_closed_after_development_pipeline_fails() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseErrorState();
        sprint.getState().changeToFinishedState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Can't change from release error to finished!");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T27_1_sprint_development_pipeline_fails_sends_notification() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        INotifier notifier = new MailNotify();
        Subscriber sub = new NotificationService(notifier);
        sprint.subscribe(scrumMaster, sub);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseErrorState();


        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Sending mail: The sprint pipeline has failed");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T28_1_development_pipeline_is_ran_again() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseErrorState();
        sprint.getState().changeToReleasingState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Re releasing pipeline..");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T29_1_sprint_release_is_cancelled() throws ChangeSprintStateException, InterruptedException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToReleaseErrorState();
        sprint.getState().changeToReleaseCancelledState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Sprint release has been cancelled");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T30_1_sprint_review_is_uploaded_at_the_end_of_a_sprint() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        Report report = new Report();

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();

        sprint.addReport(report);

        // Assert
        assertEquals(sprint.getReport(), report);
    }

    @Test
    public void T30_2_sprint_review_is_uploaded_before_the_end_of_a_sprint() {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        Report report = new Report();

        // Act
        sprint.addReport(report);

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Cannot add a report in this stage!");
        assertNull(sprint.getReport());
        consoleCaptor.clearOutput();
    }

    @Test
    public void T31_1_sprint_is_closed_after_uploading_review() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);
        Report report = new Report();

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();

        sprint.addReport(report);
        sprint.getState().changeToReviewedState();
        ISprintState state = sprint.getState();

        // Assert
        assertEquals(state.getClass(), ReviewedState.class);
    }

    @Test(expectedExceptions = ChangeSprintStateException.class)
    public void T31_2_sprint_is_closed_before_uploading_review() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReviewedState();

        ISprintState state = sprint.getState();

        // Assert
        assertEquals(state.getClass(), FinishedState.class);
        assert(consoleCaptor.getStandardOutput()).contains("Can't change from finished to reviewed without submitting a report!");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T32_1_sprint_can_be_finished() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();

        ISprintState state = sprint.getState();

        // Assert
        assertEquals(state.getClass(), FinishedState.class);
    }

    @Test(expectedExceptions = ChangeSprintStateException.class)
    public void T33_1_sprint_status_cannot_be_changed_during_pipeline() throws ChangeSprintStateException {
        // Arrange
        Sprint sprint = new Sprint(release,"Sprint 1", backlog, scrumMaster, productOwner, devs, testers, project, date, date);

        // Act
        sprint.getState().changeToInProgressState();
        sprint.getState().changeToFinishedState();
        sprint.getState().changeToReleasingState();
        sprint.getState().changeToInProgressState();

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Can't change state while releasing!");
        consoleCaptor.clearOutput();
    }

}
