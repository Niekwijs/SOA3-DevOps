package sprint.states;

import sprint.Sprint;
import exceptions.ChangeSprintStateException;

public class InProgressState implements ISprintState {

    private final Sprint sprint;

    public InProgressState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void changeToInitialState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from in progress to inital!");
    }

    @Override
    public void changeToInProgressState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from in progress to in progress!");
    }

    @Override
    public void changeToFinishedState() {
        this.sprint.setState(new FinishedState(this.sprint));
    }

    @Override
    public void changeToReleasingState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from in progress to releasing!");
    }

    @Override
    public void changeToReleaseCancelledState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from in progress to release cancelled!");
    }

    @Override
    public void changeToReleaseErrorState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from in progress to release error!");
    }

    @Override
    public void changeToReleaseSuccessState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from in progress to release success!");
    }

    @Override
    public void changeToReviewedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from in progress to reviewed!");
    }
}
