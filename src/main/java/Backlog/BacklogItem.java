package Backlog;

import account.*;
import notification.Subscriber;
import forum.DiscussionThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BacklogItem {

    public String description;
    public int value;
    public int estimate;
    public int priority;
    public List<Activity> activities;
    public Developer assignedTo;
    private final DiscussionThread thread;

    public IBacklogItemState state;

    public Map<Account, Subscriber> subscribers = new HashMap<Account, Subscriber>();

    public BacklogItem(String description, int value, int estimate, int priority) throws Exception {
        if(value<= 0){
            throw new IllegalArgumentException ("Value cant be null or negative");
        }
        if(estimate <=0){
            throw new IllegalArgumentException ("Estimate cant be null or negative");
        }
        if(priority <= 0){
            throw new IllegalArgumentException ("Estimate cant be null or negative");
        }
        this.description = description;
        this.value = value;
        this.estimate = estimate;
        this.priority = priority;
        this.state = new ToDoState(this);
        this.activities = new ArrayList<Activity>();
        this.thread = new DiscussionThread(this.description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    public Activity getActivity(int index) {
        return activities.get(index);
    }

    public boolean activitiesDone() {
        for (Activity activity : activities) {
            if (!activity.getDone()) {
                return false;
            }
        }
        return true;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {

        this.priority = priority;
    }

    public void setAssignedTo(Developer developer) {
        this.assignedTo = developer;
    }

    public Developer getAssignedTo() {
        return assignedTo;
    }

    public IBacklogItemState getState() {
        return state;
    }

    public void setState(IBacklogItemState state) {
        this.state = state;
    }
    public Class<? extends IBacklogItemState> getCurrentState(){
        return this.state.getClass();
    }

    public void subscribe(Account account, Subscriber s) {
        this.subscribers.put(account, s);
    }

    public void unsubscribe(Account account) {
        this.subscribers.remove(account);
    }

    public void notifySpecificSubscribers(String accountTypes, String message) {

        for (Map.Entry<Account, Subscriber> entry : subscribers.entrySet()) {
            if(entry.getKey().getClass().getSimpleName().equals(accountTypes)){
                entry.getValue().update(message);
            }
        }
    }

    public void addCommentToThread(String comment){
        this.thread.addComment(comment);
    }

    public DiscussionThread getThread(){
        return this.thread;
    }

}
