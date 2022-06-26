package backlog;

import account.Account;
import exceptions.ChangeBacklogStateException;

public class ReadyForTestingState implements IBacklogItemState {


    private BacklogItem _backlogItem;

    public ReadyForTestingState(BacklogItem context) {
        this._backlogItem = context;
    }

    @Override
    public void changeToToDoState()  {
        this._backlogItem.setState(new ToDoState(this._backlogItem));
        this._backlogItem.notifySpecificSubscribers("ScrumMaster", "Change from ready to testing to doing");
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
