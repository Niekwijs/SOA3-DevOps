package scm;

import java.util.ArrayList;

public class PullRequest {

    private final int id;
    private final String title;
    private final String description;
    private ArrayList<Commit> commitList;

    public PullRequest(int id, String title, String description, ArrayList<Commit> commitList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.commitList = commitList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Commit> getCommitList() {
        return commitList;
    }

    public void setCommitList(ArrayList<Commit> commitList) {
        this.commitList = commitList;
    }
}
