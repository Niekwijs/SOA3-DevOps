package notification;

public class SlackNotify implements INotifier {

    public void sendNotification(String message) {
        System.out.println("Sent Slack message: " + message);
    }
}
