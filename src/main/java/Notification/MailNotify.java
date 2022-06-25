package notification;

public class MailNotify implements INotifier{

    private final MailLib adaptor;

    public MailNotify(){
        this.adaptor = new MailLib();
    }

    @Override
    public void sendNotification(String message) {
        adaptor.sendMail(message);
    }
}
