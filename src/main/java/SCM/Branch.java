package scm;

import java.util.ArrayList;

public class Branch {

    private final int id;
    private String name;
    private final ArrayList<Commit> commits;

    public Branch(int id, String name) {
        this.id = id;
        this.name = name;
        this.commits = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Commit> getCommits() {
        return commits;
    }

    public void addCommit(Commit commit) {
        this.commits.add(commit);
    }

    public Commit getCommitAtIndex(int index) {
        return this.commits.get(index);
    }

}
