package account;

public abstract class Account {

    public final String name;
    public final int employeeNumber;
    public final String email;
    public final String phoneNUmber;
    public final String slackUsername;

    protected Account(String name, int employeeNumber, String email, String phoneNUmber, String slackUsername) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.email = email;
        this.phoneNUmber = phoneNUmber;
        this.slackUsername = slackUsername;
    }

}
