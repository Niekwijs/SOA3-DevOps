package Project;

import Account.Account;

public interface IProject {
    String getName();
    Account getProductOwner();
    void setProductOwner(Account productOwner);
}
