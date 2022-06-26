package backlog;

import account.Account;
import exceptions.ChangeBacklogStateException;

public interface IBacklogItemState {
    void changeToToDoState() throws ChangeBacklogStateException;
    void changeToDoingState() throws ChangeBacklogStateException;
    void changeToReadyForTestingState(Account account) throws ChangeBacklogStateException;
    void changeToTestingState() throws ChangeBacklogStateException;
    void changeToTestedState() throws ChangeBacklogStateException;
    void changeToDoneState() throws ChangeBacklogStateException;

}
