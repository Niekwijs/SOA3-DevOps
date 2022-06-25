package project;

import account.Account;
import Backlog.Backlog;

public class KanbanProject implements IProject{

    private final String name;
    private Account productOwner;
    private final Backlog projectBacklog;

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
