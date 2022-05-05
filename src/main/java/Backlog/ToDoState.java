package Backlog;

import Account.Account;
import exceptions.ChangeBacklogStateException;

public class ToDoState implements IBacklogItemState {

    private BacklogItem _backlogItem;

    public ToDoState(BacklogItem context) {
        this._backlogItem = context;
    }

    @Override
    public void changeToToDoState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ToDo to ToDo");
    }

    @Override
    public void changeToDoingState() {
        _backlogItem.setState(new DoingState(this._backlogItem));
    }

    @Override
    public void changeToReadyForTestingState(Account account) throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ToDo to ReadyForTesting!");
    }

    @Override
    public void changeToTestingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ToDo to Testing!");
    }

    @Override
    public void changeToTestedState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ToDo to Tested!");
    }

    @Override
    public void changeToDoneState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ToDo to ReadyForTesting!");
    }
}
