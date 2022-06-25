package Notification;

import nl.altindag.console.ConsoleCaptor;
import org.testng.annotations.Test;

public class NotificationTests {

    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Test
    public void T44_1_send_notification_via_slack() {
        // Arrange
        INotifier notifier = new SlackNotify();

        // Act
        notifier.sendNotification("This is a slack message");

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Sent Slack message: This is a slack message");
        consoleCaptor.clearOutput();
    }

    @Test
    public void T44_2_send_notification_via_mail() {
        // Arrange
        INotifier notifier = new MailNotify();

        // Act
        notifier.sendNotification("This is a mail");

        // Assert
        assert(consoleCaptor.getStandardOutput()).contains("Sending mail: This is a mail");
        consoleCaptor.clearOutput();
    }

}
