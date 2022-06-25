package Backlog;

import Account.Account;
import Account.ScrumMaster;
import Account.ProductOwner;
import Account.Tester;
import Account.Developer;
import Notification.NotificationService;
import Notification.SlackNotify;
import Project.IProject;
import Project.ProjectFactory;
import Sprint.SprintType;
import exceptions.ChangeBacklogStateException;
import nl.altindag.console.ConsoleCaptor;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.testng.AssertJUnit.assertEquals;

public class BacklogTests {

    ProjectFactory projectFactory = new ProjectFactory();

    SprintType release = SprintType.Release;
    SprintType review = SprintType.Review;
    Backlog backlog = new Backlog();
    Account scrumMaster = new ScrumMaster("testScrumMaster", 1, "test@email.com", "0612345678", "testUser");
    Account productOwner = new ProductOwner("testProductOwner", 2, "test@email.com", "0612345678", "testUser");
    ArrayList<Account> devs = new ArrayList<Account>();
    ArrayList<Account> testers = new ArrayList<Account>();
    IProject project = projectFactory.getProject("scrum", "Project 1");
    Date date = new Date();
    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Test
    public void T2_1_backlog_item_can_be_created_with_correct_data(){
        //Arrange

        // Act

        BacklogItem backlogItem = null;
        try {
            backlogItem = new BacklogItem("test backlog", 1, 2, 3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Assert
        assertEquals(backlogItem.description,"test backlog");
        assertEquals(backlogItem.value,1);
        assertEquals(backlogItem.estimate,2);
        assertEquals(backlogItem.priority,3);
    }

    @Test
    public void T2_2_check_if_backlog_item_is_created_with_empty_activities(){
        //Arrange

        //Act
        BacklogItem backlogItem = null;
        try {
            backlogItem = new BacklogItem("test backlog", 1, 2, 3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Assert
        assertEquals(backlogItem.activities, Collections.emptyList() );
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void T2_3_1_check_if_backlog_item_cant_be_created_with_negative_values() throws Exception {
        //Arrange

        //Act

        //Assert
        BacklogItem backlogItem = new BacklogItem("test backlog", -1, 2, 3);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void T2_3_2_check_if_backlog_item_cant_be_created_with_negative_values() throws Exception {
        //Arrange

        //Act

        //Assert
        BacklogItem backlogItem = new BacklogItem("test backlog", 1, -1, 3);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void T2_3_3_check_if_backlog_item_cant_be_created_with_negative_values() throws Exception {
        //Arrange

        //Act

        //Assert
        BacklogItem backlogItem = new BacklogItem("test backlog", 1, 2, -1);
    }
    @Test
    public void T3_1_check_if_activities_ar_added_correctly_to_backlog_item() throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        //Act
        backlogItem.addActivity(activity);
        //Assert
        assertEquals(backlogItem.getActivity(0), activity);
    }
    @Test
    public void T3_2_check_if_activities_are_returned_correct () throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.addActivity(activity);
        //Act
        Activity activityRes = backlogItem.getActivity(0);
        //Assert
        assertEquals(activity, activityRes);
    }
    @Test
    public void T3_3_check_if_backlog_item_can_be_set_to_done_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();

        //Act
        backlogItem.state.changeToDoneState();
        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test
    public void F3_4_Check_if_done_state_can_be_changed_to_todo_state () throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.addActivity(activity);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();
        activity.completeActivity();
        backlogItem.state.changeToDoneState();

        //Act
        backlogItem.state.changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F3_5_check_if_backlog_item_cant_be_set_to_done_state_when_activities_arent_done () throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.addActivity(activity);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();
//        activity.completeActivity();
        backlogItem.state.changeToDoneState();

        //Act
        backlogItem.state.changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test
    public void F3_6_check_if_backlog_item_can_be_changed_to_done_when_all_activities_are_done  () throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.addActivity(activity);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();



        //Act
        activity.completeActivity();
        backlogItem.state.changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test
    public void F4_1_check_if_a_developer_can_be_added_to_a_backlog_item () throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Developer developer = new Developer("test", 1,"test@mial.com","01111111","tester45");
        //Act
        backlogItem.setAssignedTo(developer);
        //Assert
        assertEquals(backlogItem.getAssignedTo(), developer);
    }
    @Test
    public void F5_1_check_if_a_second_developer_can_be_assigned_to_an_backlog_item() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Developer developer = new Developer("test", 1,"test@mial.com","01111111","tester45");
        Developer developer2 = new Developer("test2", 2,"test2@mial.com","01111112","tester46");

        //Act
        backlogItem.setAssignedTo(developer);
        backlogItem.setAssignedTo(developer2);
        //Assert
        assertEquals(backlogItem.getAssignedTo(), developer2);
    }
    @Test
    public void F6_1_check_if_a_backlog_item_can_be_changed_to_the_doing_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);

        //Act
        backlogItem.state.changeToDoingState();

        //Assert
        assertEquals(backlogItem.state.getClass(), DoingState.class);

    }
    @Test
    public void F6_2_check_if_a_backlog_item_can_be_changed_to_the_ready_for_testing_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.state.changeToDoingState();
        //Act
        backlogItem.state.changeToReadyForTestingState();

        //Assert
        assertEquals(backlogItem.state.getClass(), ReadyForTestingState.class);
    }
    @Test
    public void F6_3_check_if_a_backlog_item_can_be_changed_to_the_testing_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();

        //Act
        backlogItem.state.changeToTestingState();

        //Assert
        assertEquals(backlogItem.state.getClass(),TestingState.class);

    }
    @Test
    public void F6_4_check_if_a_backlog_item_can_be_changed_to_the_tested_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();

        //Act
        backlogItem.state.changeToTestedState();

        //Assert
        assertEquals(backlogItem.state.getClass(),TestedState.class);
    }
    @Test
    public void F6_5_check_if_a_backlog_item_can_be_changed_to_the_tested_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();

        //Act
        backlogItem.state.changeToDoneState();

        //Assert
        assertEquals(backlogItem.state.getClass(), DoneState.class);
    }
    @Test
    public void F7_1_check_if_a_backlog_item_state_when_initialized_is_todo_state() throws Exception {
        //Arrange


        //Act
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);

        //Assert
        assertEquals(backlogItem.state.getClass(), ToDoState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F8_9_1_check_if_backlog_item_cant_be_changed_to_done_state_when_0_out_2_activities_are_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Activity activity1 = new Activity("testActivity1");
        Activity activity2 = new Activity("testActivity2");
        backlogItem.addActivity(activity1);
        backlogItem.addActivity(activity2);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();


        //Act
        backlogItem.state.changeToDoneState();

        //Assert
        assertEquals(backlogItem.state.getClass(), TestedState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F8_9_2_check_if_backlog_item_cant_be_changed_to_done_state_when_1_out_2_activities_are_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Activity activity1 = new Activity("testActivity1");
        Activity activity2 = new Activity("testActivity2");
        backlogItem.addActivity(activity1);
        backlogItem.addActivity(activity2);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();


        //Act
        activity1.completeActivity();
        backlogItem.state.changeToDoneState();

        //Assert
        assertEquals(backlogItem.state.getClass(), TestedState.class);

    }
    @Test
    public void F8_9_3_check_if_backlog_item_can_be_changed_to_done_state_when_2_out_2_activities_are_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Activity activity1 = new Activity("testActivity1");
        Activity activity2 = new Activity("testActivity2");
        backlogItem.addActivity(activity1);
        backlogItem.addActivity(activity2);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();
        backlogItem.state.changeToTestingState();
        backlogItem.state.changeToTestedState();


        //Act
        activity1.completeActivity();
        activity2.completeActivity();
        backlogItem.state.changeToDoneState();

        //Assert
        assertEquals(backlogItem.state.getClass(), DoneState.class);

    }
    @Test
    public void F10_11_1_check_if_all_states_are_being_changed() throws Exception {
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);

        assertEquals(backlogItem.state.getClass(), ToDoState.class);
        backlogItem.state.changeToDoingState();
        assertEquals(backlogItem.state.getClass(), DoingState.class);
        backlogItem.state.changeToReadyForTestingState();
        assertEquals(backlogItem.state.getClass(), ReadyForTestingState.class);
        backlogItem.state.changeToTestingState();
        assertEquals(backlogItem.state.getClass(), TestingState.class);
        backlogItem.state.changeToTestedState();
        assertEquals(backlogItem.state.getClass(), TestedState.class);
        backlogItem.state.changeToDoneState();
        assertEquals(backlogItem.state.getClass(), DoneState.class);

    }
    @Test
    public void F12_1_check_if_messages_isBeing_send_to_testers_when_backlog_item_is_set_to_ready_for_testing() throws Exception {
        //Assert
        Tester tester = new Tester("tester",1,"test@mial.com","01111111","tester45");
        NotificationService notificationService = new NotificationService(new SlackNotify());
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.subscribe(tester,notificationService);
        backlogItem.state.changeToDoingState();

        //Act
        backlogItem.state.changeToReadyForTestingState();
        //Assert

        assert(consoleCaptor.getStandardOutput()).contains("Sent Slack message: Backlog item changed from doing to ready for testing");
        consoleCaptor.clearOutput();


    }
    @Test
    public void F20_1_check_if_messages_isBeing_send_to_testers_when_backlog_item_is_set_to_ready_for_testing() throws Exception {
        //Assert
        ScrumMaster scrumMaster1 = new ScrumMaster("scrummaster",1,"test@mial.com","01111111","tester45");
        NotificationService notificationService = new NotificationService(new SlackNotify());
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.subscribe(scrumMaster1,notificationService);
        backlogItem.state.changeToDoingState();
        backlogItem.state.changeToReadyForTestingState();


        //Act
        backlogItem.state.changeToToDoState();
        //Assert

        assert(consoleCaptor.getStandardOutput()).contains("Sent Slack message: Change from ready to testing to doing");
        consoleCaptor.clearOutput();


    }

}
