package project;

import account.Account;
import Backlog.Backlog;
import sprint.Sprint;

import java.util.ArrayList;

public class ScrumProject implements IProject {

    private Account productOwner;
    public ArrayList<Sprint> sprints;
    private final String name;

    public void setProductOwner(Account productOwner) {
        this.productOwner = productOwner;
    }

    public ScrumProject(String name) {
        this.name = name;
        this.sprints = new ArrayList<>();
        Backlog projectBacklog = new Backlog();
    }

    public Account getProductOwner() {
        return productOwner;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Sprint> getSprints() {
        return sprints;
    }

    public void addSprint(Sprint sprint){
        this.sprints.add(sprint);
    }
}