package backlog;

import account.Account;

public class PriviligeCheck {

    public static boolean CheckPrivilage(Account account,Class accountType){
        return account.getClass() == accountType;
    }
}
