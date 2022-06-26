package account;

public abstract class Account {
    public String name;
    public int employeeNumber;
    public String email;
    public String phoneNUmber;
    public String slackUsername;

    public Account(String name, int employeeNumber, String email, String phoneNUmber, String slackUsername) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.email = email;
        this.phoneNUmber = phoneNUmber;
        this.slackUsername = slackUsername;
    }

}
