package project;

import account.*;

public interface IProject {
    String getName();
    Account getProductOwner();
    void setProductOwner(Account productOwner);
}
