package Backlog;

import Account.Account;
import exceptions.ChangeBacklogStateException;

public class DoneState implements IBacklogItemState {

    private BacklogItem _backlogItem;

    public DoneState(BacklogItem context) {
        this._backlogItem = context;
    }

    @Override
    public void changeToToDoState() {
        this._backlogItem.getThread().setActive(true);
        this._backlogItem.setState(new ToDoState(this._backlogItem));
    }

    @Override
    public void changeToDoingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Done to Doing!");
    }

    @Override
    public void changeToReadyForTestingState(Account account) throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Done to ReadyForTesting!");
    }

    @Override
    public void changeToTestingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Done to Testing!");
    }

    @Override
    public void changeToTestedState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Done to Tested!");
    }

    @Override
    public void changeToDoneState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Done to Done!");
    }
}
