package Project;

import Account.Account;
import Backlog.Backlog;
import Sprint.Sprint;

import java.util.ArrayList;

public class ScrumProject implements IProject {

    public Backlog projectBacklog;
    public Account productOwner;
    public ArrayList<Sprint> sprints;
    public String name;

    public void setProductOwner(Account productOwner) {
        this.productOwner = productOwner;
    }

    public ScrumProject(String name) {
        this.name = name;
        this.sprints = new ArrayList<>();
        this.projectBacklog = new Backlog();
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