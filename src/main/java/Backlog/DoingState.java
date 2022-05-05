package Backlog;

import Account.Account;
import exceptions.ChangeBacklogStateException;

public class DoingState implements IBacklogItemState {

    private BacklogItem _backlogItem;

    public DoingState(BacklogItem context) {
        this._backlogItem = context;
    }

    @Override
    public void changeToToDoState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Doing to ToDo!");
    }

    @Override
    public void changeToDoingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change from Doing to Doing!");
    }

    @Override
    public void changeToReadyForTestingState(Account account) {
        //TODO: F13 Als tester wil ik een notificatie krijgen als een backlog item in de fase Ready for testing komt zodat ik weet wanneer ik moet gaan testen
        this._backlogItem.setState(new ReadyForTestingState(this._backlogItem));

    }

    @Override
    public void changeToTestingState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change form Doing to Testing!");
    }

    @Override
    public void changeToTestedState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change form Doing to Tested!");

    }

    @Override
    public void changeToDoneState() throws ChangeBacklogStateException {
        throw new ChangeBacklogStateException("Can't change form Doing to Done!");
    }
}
