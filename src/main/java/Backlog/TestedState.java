package backlog;

import account.Account;
import account.LeadDeveloper;
import exceptions.ChangeBacklogStateException;

public class TestedState implements IBacklogItemState {

    private BacklogItem _backlogItem;

    public TestedState(BacklogItem context) {
        this._backlogItem = context;
    }

    @Override
    public void changeToToDoState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Tested to ToDo!");
    }

    @Override
    public void changeToDoingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Tested to Doing!");
    }

    @Override
    public void changeToReadyForTestingState(Account account) throws ChangeBacklogStateException {
        if(PriviligeCheck.CheckPrivilage(account, LeadDeveloper.class)){
            this._backlogItem.setState(new ReadyForTestingState(this._backlogItem));
        }else{
            throw new ChangeBacklogStateException(account.getClass().toString()+" does not have permission to change back to ready for testing");
        }

    }

    @Override
    public void changeToTestingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Tested to Testing!");
    }

    @Override
    public void changeToTestedState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Tested to Tested!");
    }

    @Override
    public void changeToDoneState() throws ChangeBacklogStateException {
        if (this._backlogItem.activitiesDone()){
            this._backlogItem.getThread().setActive(false);
            this._backlogItem.setState(new DoneState(this._backlogItem));
        } else
        {
            throw new ChangeBacklogStateException("Can't change from Tested to Done because activities are not done!");
        }
    }
}
