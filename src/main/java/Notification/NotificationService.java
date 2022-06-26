package notification;

public class NotificationService implements Subscriber {

    public INotifier notifier;

    public NotificationService(INotifier notifier) {
        this.notifier = notifier;
    }

    public void sendNotification(String message) {
        this.notifier.sendNotification(message);
    }

    public void update(String message) {
        sendNotification(message);
    }

}
