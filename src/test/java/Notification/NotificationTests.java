package notification;

import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.testng.AssertJUnit.assertTrue;

public class NotificationTests {

    @Test
    public void T44_1_send_notification_via_slack() {
        // Arrange
        INotifier notifier = new SlackNotify();

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        // Act
        notifier.sendNotification("This is a slack message");

        // Assert
        assertTrue(os.toString().contains("Sent Slack message: This is a slack message"));

    }

    @Test
    public void T44_2_send_notification_via_mail() {
        // Arrange
        INotifier notifier = new MailNotify();

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        // Act
        notifier.sendNotification("This is a mail");

        // Assert
        assertTrue(os.toString().contains("Sending mail: This is a mail"));
    }

}
