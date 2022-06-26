package notification;

public class MailNotify implements INotifier{

    public MailLib adaptor;

    public MailNotify(){
        this.adaptor = new MailLib();
    }

    @Override
    public void sendNotification(String message) {
        adaptor.sendMail(message);
    }
}
