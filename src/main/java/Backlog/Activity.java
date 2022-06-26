package backlog;

public class Activity {

    public String description;
    public Boolean done;

    public Activity(String description) {
        this.description = description;
        this.done = false;
    }
    public void completeActivity(){
        this.done = true;
    }
    public void unCompleteActivity(){
        this.done = false;
    }


    public Boolean getDone() {
        return done;
    }
    public String getDescription() {
        return description;
    }
}
