package Forum;

import Account.Account;
import Notification.Publisher;
import Notification.Subscriber;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DiscussionThread implements Publisher {

    public TreeNode treeNode;
    private boolean active;
    private ArrayList<Subscriber> subscribers = new ArrayList<>();


    public DiscussionThread(String name) {
        this.treeNode = new TreeNode(new Comment("New thread: " + name, new Date()));
        this.active = true;
    }

    public void addComment(String comment){
        TreeNode newComment = new TreeNode(new Comment(comment, new Date()));
        this.treeNode.addTreeNode(newComment);
        this.notifySubscribers("Received a new comment; " + comment);
    }

    public String getThread(){
        return this.treeNode.toString();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void subscribe(Subscriber s) {
        this.subscribers.add(s);
    }

    @Override
    public void unsubscribe(Subscriber s) {
        this.subscribers.remove(s);
    }

    @Override
    public void notifySubscribers(String message) {
        for(Subscriber s :this.subscribers){
            s.update(message);
        }
    }
}
