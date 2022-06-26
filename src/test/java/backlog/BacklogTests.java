package backlog;

import account.Account;
import account.ScrumMaster;
import account.ProductOwner;
import account.Tester;
import account.Developer;
import account.LeadDeveloper;
import notification.NotificationService;
import notification.SlackNotify;
import exceptions.ChangeBacklogStateException;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.testng.AssertJUnit.*;

public class BacklogTests {

    Account scrumMaster = new ScrumMaster("testScrumMaster", 1, "test@email.com", "0612345678", "testUser");
    Account productOwner = new ProductOwner("testProductOwner", 2, "test@email.com", "0612345678", "testUser");
    Developer developer = new Developer("testDev", 1,"test@mial.com","01111111","tester45" );
    LeadDeveloper leadDeveloper = new LeadDeveloper("testDev", 1,"test@mial.com","01111111","tester45" );
    Developer developer2 = new Developer("test2", 2,"test2@mial.com","01111112","tester46");

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
        assertEquals(backlogItem.getDescription(),"test backlog");
        assertEquals(backlogItem.getValue(),1);
        assertEquals(backlogItem.getEstimate(),2);
        assertEquals(backlogItem.getPriority(),3);
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
        assertEquals(backlogItem.getActivities(), Collections.emptyList() );
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
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();

        //Act
        backlogItem.getState().changeToDoneState();
        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test
    public void F3_4_Check_if_done_state_can_be_changed_to_todo_state () throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.addActivity(activity);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        activity.completeActivity();
        backlogItem.getState().changeToDoneState();

        //Act
        backlogItem.getState().changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F3_5_check_if_backlog_item_cant_be_set_to_done_state_when_activities_arent_done () throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.addActivity(activity);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
//        activity.completeActivity();
        backlogItem.getState().changeToDoneState();

        //Act
        backlogItem.getState().changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test
    public void F3_6_check_if_backlog_item_can_be_changed_to_done_when_all_activities_are_done  () throws Exception {
        //Arrange
        Activity activity = new Activity("test activity");
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.addActivity(activity);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();



        //Act
        activity.completeActivity();
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test
    public void F4_1_check_if_a_developer_can_be_added_to_a_backlog_item () throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        //Act
        backlogItem.setAssignedTo(developer);
        //Assert
        assertEquals(backlogItem.getAssignedTo(), developer);
    }
    @Test
    public void F5_1_check_if_a_second_developer_can_be_assigned_to_an_backlog_item() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);

        //Act
        backlogItem.setAssignedTo(developer);
        backlogItem.setAssignedTo(developer2);

        //Assert
        assertEquals(backlogItem.getAssignedTo(), developer2);
    }
    @Test
    public void F6_1_1_check_if_a_backlog_item_can_be_changed_from_todo_to_the_doing_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);

        //Act
        backlogItem.getState().changeToDoingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoingState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_1_2_check_if_a_backlog_item_cant_be_changed_from_todo_to_the_ready_for_testing_state() throws Exception {
        //Arrange
        Developer developer = new Developer("testDev", 1,"test@mial.com","01111111","tester45" );
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);


        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoingState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_1_3_check_if_a_backlog_item_cant_be_changed_from_todo_to_the_testing_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);


        //Act
        backlogItem.getState().changeToTestingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoingState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_1_4_check_if_a_backlog_item_cant_be_changed_from_todo_to_the_tested_state() throws Exception {
        //Arrange

        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);


        //Act
        backlogItem.getState().changeToTestedState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoingState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_1_5_check_if_a_backlog_item_cant_be_changed_from_todo_to_the_done_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);


        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoingState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_2_1_check_if_a_backlog_item_can_be_changed_from_doing_to_the_todo() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        //Act
        backlogItem.getState().changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_2_2_check_if_a_backlog_item_can_be_changed_from_doing_to_the_doing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        //Act
        backlogItem.getState().changeToDoingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoingState.class);
    }
    @Test
    public void F6_2_3_check_if_a_backlog_item_can_be_changed_from_doing_to_the_RFT() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);

        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_2_4_check_if_a_backlog_item_can_be_changed_from_doing_to_the_testing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        //Act
        backlogItem.getState().changeToTestingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_2_5_check_if_a_backlog_item_can_be_changed_from_doing_to_the_tested() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        //Act
        backlogItem.getState().changeToTestedState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_2_5_check_if_a_backlog_item_can_be_changed_from_doing_to_the_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test
    public void F6_3_1_check_if_a_backlog_item_can_be_changed_form_RFT_to_todo() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        //Act
        backlogItem.getState().changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_3_2_check_if_a_backlog_item_can_be_changed_form_RFT_to_doing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        //Act
        backlogItem.getState().changeToDoingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_3_3_check_if_a_backlog_item_can_be_changed_form_RFT_to_RFT() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);

        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test
    public void F6_3_4_check_if_a_backlog_item_can_be_changed_form_RFT_to_Testing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        //Act
        backlogItem.getState().changeToTestingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestingState.class);
    }
    @Test
    public void F6_3_5_check_if_a_backlog_item_can_be_changed_form_RFT_to_Tested() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        //Act
        backlogItem.getState().changeToTestedState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_3_6_check_if_a_backlog_item_can_be_changed_form_RFT_to_Done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test
    public void F6_4_1_check_if_a_backlog_item_can_be_changed_form_testing_to_todo() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        //Act
        backlogItem.getState().changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_4_2_check_if_a_backlog_item_can_be_changed_form_testing_to_doing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        //Act
        backlogItem.getState().changeToDoingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_4_3_check_if_a_backlog_item_can_be_changed_form_testing_to_RFT() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_4_4_check_if_a_backlog_item_can_be_changed_form_testing_to_testing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        //Act
        backlogItem.getState().changeToTestingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestingState.class);
    }
    @Test
    public void F6_4_5_check_if_a_backlog_item_can_be_changed_form_testing_to_tested() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        //Act
        backlogItem.getState().changeToTestedState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_4_6_check_if_a_backlog_item_can_be_changed_form_testing_to_Done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestingState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_5_1_check_if_a_backlog_item_can_be_changed_form_tested_to_todo() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        //Act
        backlogItem.getState().changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_5_2_check_if_a_backlog_item_can_be_changed_form_tested_to_doing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        //Act
        backlogItem.getState().changeToDoingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_5_3_check_if_a_backlog_item_can_be_changed_form_tested_to_RFT() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_5_4_check_if_a_backlog_item_can_be_changed_form_tested_to_testing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        //Act
        backlogItem.getState().changeToTestingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_5_5_check_if_a_backlog_item_can_be_changed_form_tested_to_tested() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        //Act
        backlogItem.getState().changeToTestedState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
    }
    @Test
    public void F6_5_6_check_if_a_backlog_item_can_be_changed_form_tested_to_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test
    public void F6_6_1_check_if_a_backlog_item_can_be_changed_form_done_to_todo() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        backlogItem.getState().changeToDoneState();
        //Act
        backlogItem.getState().changeToToDoState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_6_2_check_if_a_backlog_item_can_be_changed_form_done_to_doing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        backlogItem.getState().changeToDoneState();
        //Act
        backlogItem.getState().changeToDoingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_6_3_check_if_a_backlog_item_can_be_changed_form_done_to_RFT() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        backlogItem.getState().changeToDoneState();
        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_6_4_check_if_a_backlog_item_can_be_changed_form_done_to_testing() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        backlogItem.getState().changeToDoneState();
        //Act
        backlogItem.getState().changeToTestingState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_6_5_check_if_a_backlog_item_can_be_changed_form_done_to_tested() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        backlogItem.getState().changeToDoneState();
        //Act
        backlogItem.getState().changeToTestedState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F6_6_6_check_if_a_backlog_item_can_be_changed_form_done_to_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        backlogItem.getState().changeToDoneState();
        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test
    public void F6_4_check_if_a_backlog_item_can_be_changed_to_the_tested_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();

        //Act
        backlogItem.getState().changeToTestedState();

        //Assert
        assertEquals(backlogItem.getState().getClass(),TestedState.class);
    }
    @Test
    public void F6_5_check_if_a_backlog_item_can_be_changed_to_the_tested_state() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();

        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);
    }
    @Test
    public void F7_1_check_if_a_backlog_item_state_when_initialized_is_todo_state() throws Exception {
        //Arrange


        //Act
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);

        //Assert
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F8_9_1_check_if_backlog_item_cant_be_changed_to_done_state_when_0_out_2_activities_are_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Activity activity1 = new Activity("testActivity1");
        Activity activity2 = new Activity("testActivity2");
        backlogItem.addActivity(activity1);
        backlogItem.addActivity(activity2);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();


        //Act
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F8_9_2_check_if_backlog_item_cant_be_changed_to_done_state_when_1_out_2_activities_are_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Activity activity1 = new Activity("testActivity1");
        Activity activity2 = new Activity("testActivity2");
        backlogItem.addActivity(activity1);
        backlogItem.addActivity(activity2);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();


        //Act
        activity1.completeActivity();
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), TestedState.class);

    }
    @Test
    public void F8_9_3_check_if_backlog_item_can_be_changed_to_done_state_when_2_out_2_activities_are_done() throws Exception {
        //Arrange
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Activity activity1 = new Activity("testActivity1");
        Activity activity2 = new Activity("testActivity2");
        backlogItem.addActivity(activity1);
        backlogItem.addActivity(activity2);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();


        //Act
        activity1.completeActivity();
        activity2.completeActivity();
        backlogItem.getState().changeToDoneState();

        //Assert
        assertEquals(backlogItem.getState().getClass(), DoneState.class);

    }
    @Test
    public void F10_11_1_check_if_all_states_are_being_changed() throws Exception {
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        assertEquals(backlogItem.getState().getClass(), ToDoState.class);
        backlogItem.getState().changeToDoingState();
        assertEquals(backlogItem.getState().getClass(), DoingState.class);
        backlogItem.getState().changeToReadyForTestingState(developer);
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
        backlogItem.getState().changeToTestingState();
        assertEquals(backlogItem.getState().getClass(), TestingState.class);
        backlogItem.getState().changeToTestedState();
        assertEquals(backlogItem.getState().getClass(), TestedState.class);
        backlogItem.getState().changeToDoneState();
        assertEquals(backlogItem.getState().getClass(), DoneState.class);

    }
    @Test(expectedExceptions = ChangeBacklogStateException.class)
    public void F12_1_check_if_a_non_lead_developer_cant_change_state_from_tested_to_ready_for_testing() throws Exception {
        //Assert
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);
        backlogItem.getState().changeToTestedState();

        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);
        //Assert


    }
    @Test
    public void F12_1_check_if_a_lead_developer_can_change_state_from_tested_to_ready_for_testing() throws Exception {
        //Assert
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(leadDeveloper);
        backlogItem.getState().changeToTestedState();

        //Act
        backlogItem.getState().changeToReadyForTestingState(leadDeveloper);
        //Assert
        assertEquals(backlogItem.getState().getClass(), ReadyForTestingState.class);
    }
    @Test
    public void F13_1_check_if_messages_isBeing_send_to_testers_when_backlog_item_is_set_to_ready_for_testing() throws Exception {
        //Assert
        Tester tester = new Tester("tester",1,"test@mial.com","01111111","tester45");
        NotificationService notificationService = new NotificationService(new SlackNotify());
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.subscribe(tester,notificationService);
        backlogItem.getState().changeToDoingState();

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        //Act
        backlogItem.getState().changeToReadyForTestingState(developer);

        //Assert
        assertTrue(os.toString().contains("Sent Slack message: Backlog item changed from doing to ready for testing"));

    }
    @Test
    public void F20_1_check_if_messages_isBeing_send_to_testers_when_backlog_item_is_set_to_ready_for_testing() throws Exception {
        //Assert
        NotificationService notificationService = new NotificationService(new SlackNotify());
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        backlogItem.subscribe(scrumMaster,notificationService);
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState(developer);

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        //Act
        backlogItem.getState().changeToToDoState();
        //Assert

        assertTrue(os.toString().contains("Sent Slack message: Change from ready to testing to doing"));
    }

    @Test
    public void test_privilige_check() {
        // Arrange

        // Act
        boolean resTrue = PriviligeCheck.checkPrivilege(productOwner, ProductOwner.class.toString());
        boolean resFalse = PriviligeCheck.checkPrivilege(scrumMaster, ProductOwner.class.toString());

        // Assert
        assertTrue(resTrue);
        assertFalse(resFalse);
    }
}
