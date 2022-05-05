package Backlog;

import Account.Account;
import exceptions.ChangeBacklogStateException;

public class ReadyForTestingState implements IBacklogItemState {


    private BacklogItem _backlogItem;

    public ReadyForTestingState(BacklogItem context) {
        this._backlogItem = context;
    }

    @Override
    public void changeToToDoState()  {
        //TODO: F20 Als scrum master wil ik een notificatie krijgen als een backlog item van Ready for testing naar Todo wordt verplaatst zodat ik hiernaar kan handelen
        this._backlogItem.setState(new ToDoState(this._backlogItem));
    }

    @Override
    public void changeToDoingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ReadyForTesting to Doing!");

    }

    @Override
    public void changeToReadyForTestingState(Account account) throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ReadyForTesting to ReadyForTesting!");

    }

    @Override
    public void changeToTestingState() {
        this._backlogItem.setState(new TestingState(this._backlogItem));
    }

    @Override
    public void changeToTestedState() {
            this._backlogItem.setState(new TestedState(this._backlogItem));
    }

    @Override
    public void changeToDoneState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from ReadyForTesting to Done!");

    }
}
