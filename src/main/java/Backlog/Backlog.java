package backlog;

import java.util.ArrayList;
import java.util.List;

public class Backlog {
    public List<BacklogItem> backlogItems;

    public Backlog() {
        this.backlogItems = new ArrayList<>();

    }
    public Backlog(List<BacklogItem> backlogItems) {
        this.backlogItems = backlogItems;
    }

    public void addBacklogItem(BacklogItem backlogItem){
        this.backlogItems.add(backlogItem);
    }
    public List<BacklogItem> getBacklogItems(){
        return backlogItems;
    }
}
