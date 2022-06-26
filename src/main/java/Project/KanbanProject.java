package project;

import account.Account;
import backlog.Backlog;

public class KanbanProject implements IProject{

    public String name;
    public Account productOwner;
    public Backlog projectBacklog;

    public KanbanProject(String name) {
        this.name = name;
        this.projectBacklog = new Backlog();
    }

    public void setProductOwner(Account productOwner) {
        this.productOwner = productOwner;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Account getProductOwner() {
        return this.productOwner;
    }

    public Backlog getProjectBacklog() {
        return this.projectBacklog;
    }
}
