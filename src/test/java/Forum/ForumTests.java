package Forum;

import Backlog.*;
import Notification.INotifier;
import Notification.MailNotify;
import Notification.NotificationService;
import Notification.Subscriber;
import exceptions.ChangeBacklogStateException;
import nl.altindag.console.ConsoleCaptor;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class ForumTests {

    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Test
    public void T34_1_backlog_item_is_created_with_empty_thread() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);

        // Act
        DiscussionThread thread = backlogItem.getThread();

        // Assert
        assertNotNull(thread);
        assertEquals(0, thread.getComments());
    }

    @Test
    public void T35_1_can_respond_to_discussion_thread() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);

        // Act
        DiscussionThread thread = backlogItem.getThread();
        int initialComments = thread.getComments();
        thread.addComment("new comment");
        int newComments = thread.getComments();

        // Assert
        assertEquals(0, initialComments);
        assertEquals(1, newComments);
    }

    @Test
    public void T35_2_can_respond_to_discussion_thread_when_active() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);

        // Act
        DiscussionThread thread = backlogItem.getThread();
        int initialComments = thread.getComments();
        boolean active = thread.isActive();
        thread.addComment("new comment");
        int newComments = thread.getComments();

        // Assert
        assertEquals(0, initialComments);
        assertTrue(active);
        assertEquals(1, newComments);
    }

    @Test
    public void T35_3_can_not_respond_to_discussion_thread_when_inactive() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);

        // Act
        DiscussionThread thread = backlogItem.getThread();
        int initialComments = thread.getComments();

        thread.setActive(false);
        boolean active = thread.isActive();

        thread.addComment("new comment");
        int newComments = thread.getComments();

        // Assert
        assertEquals(0, initialComments);
        assertFalse(active);
        assertEquals(0, newComments);
    }

    @Test
    public void T36_1_subscribers_of_discussion_thread_receive_notification() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);
        DiscussionThread thread = backlogItem.getThread();

        INotifier notifier = new MailNotify();
        Subscriber sub = new NotificationService(notifier);
        thread.subscribe(sub);

        // Act
        thread.addComment("new comment");

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Sending mail: Received a new comment; new comment");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T37_38_1_thread_status_should_be_true_when_status_todo() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);

        // Act
        IBacklogItemState state = backlogItem.getState();

        DiscussionThread thread = backlogItem.getThread();
        boolean threadState = thread.isActive();

        // Assert
        assertEquals(ToDoState.class, state.getClass());
        assertTrue(threadState);
    }

    @Test
    public void T37_38_2_thread_status_should_be_true_when_status_in_progress() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);

        // Act
        backlogItem.getState().changeToDoingState();
        IBacklogItemState state = backlogItem.getState();

        DiscussionThread thread = backlogItem.getThread();
        boolean threadState = thread.isActive();

        // Assert
        assertEquals(DoingState.class, state.getClass());
        assertTrue(threadState);
    }

    @Test
    public void T37_38_2_thread_status_should_be_false_when_status_done() throws Exception {
        // Arrange
        BacklogItem backlogItem = new BacklogItem("BacklogItem", 1, 1, 2);

        // Act
        backlogItem.getState().changeToDoingState();
        backlogItem.getState().changeToReadyForTestingState();
        backlogItem.getState().changeToTestingState();
        backlogItem.getState().changeToTestedState();
        backlogItem.getState().changeToDoneState();

        IBacklogItemState state = backlogItem.getState();
        DiscussionThread thread = backlogItem.getThread();
        boolean threadState = thread.isActive();

        // Assert
        assertEquals(DoneState.class, state.getClass());
        assertFalse(threadState);
    }

}
