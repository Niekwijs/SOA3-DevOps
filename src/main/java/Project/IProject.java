package project;

import account.Account;

public interface IProject {
    String getName();
    Account getProductOwner();
    void setProductOwner(Account productOwner);
}
