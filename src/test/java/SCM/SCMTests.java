package scm;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class SCMTests {

    @Test
    public void T42_1_branch_is_created(){
        // Arrange
        Branch branch = new Branch(1, "dev");

        // Act
        String name = branch.getName();
        int id = branch.getId();
        ArrayList<Commit> commits = branch.getCommits();

        // Assert
        assertEquals(name, "dev");
        assertEquals(id, 1);
        assertEquals(commits.size(), 0);
    }

    @Test
    public void T42_2_commit_is_created(){
        // Arrange
        Commit commit = new Commit(1, "Initial commit", new ArrayList<>());

        // Act
        String msg = commit.getMessage();
        int id = commit.getId();
        ArrayList<String> files = commit.getFiles();

        // Assert
        assertEquals(msg, "Initial commit");
        assertEquals(id, 1);
        assertEquals(files.size(), 0);
    }

    @Test
    public void T42_3_PR_is_created(){
        // Arrange
        PullRequest pullRequest = new PullRequest(1, "PR1", "Initial PR", new ArrayList<Commit>());

        // Act
        int id = pullRequest.getId();
        String title = pullRequest.getTitle();
        String description = pullRequest.getDescription();
        ArrayList<Commit> commits = pullRequest.getCommitList();

        // Assert
        assertEquals(id, 1);
        assertEquals(title, "PR1");
        assertEquals(description, "Initial PR");
        assertEquals(commits.size(), 0);
    }

    @Test
    public void T42_4_commit_is_added_to_branch(){
        // Arrange
        Branch branch = new Branch(1, "dev");
        Commit commit = new Commit(1, "Initial commit", new ArrayList<>());

        // Act
        branch.addCommit(commit);

        // Assert
        assertEquals(branch.getCommits().size(), 1);
        assertEquals(branch.getCommitAtIndex(0), commit);
    }

    @Test
    public void T42_5_PR_is_made_with_list_of_commits(){
        // Arrange
        PullRequest pullRequest = new PullRequest(1, "PR1", "Initial PR", new ArrayList<Commit>());
        Commit commit1 = new Commit(1, "Initial commit", new ArrayList<>());
        Commit commit2 = new Commit(2, "Second commit", new ArrayList<>());

        // Act
        ArrayList<Commit> commits = new ArrayList<>();
        commits.add(commit1);
        commits.add(commit2);

        pullRequest.setCommitList(commits);

        // Assert
        assertEquals(pullRequest.getCommitList().size(), 2);
    }

}