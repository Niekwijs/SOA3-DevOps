package Sprint.States;

import Sprint.Sprint;
import exceptions.ChangeSprintStateException;

public class FinishedState implements ISprintState {

    private Sprint sprint;

    public FinishedState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void changeToInitialState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from finished to inital!");
    }

    @Override
    public void changeToInProgressState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from finished to inital!");
    }

    @Override
    public void changeToFinishedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from finished to finished!");
    }

    @Override
    public void changeToReleasingState() {
        this.sprint.setState(new ReleasingState(this.sprint));
    }

    @Override
    public void changeToReleaseCancelledState() {
        this.sprint.setState(new ReleaseCancelledState(this.sprint));
    }

    @Override
    public void changeToReleaseErrorState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from finished to release error!");
    }

    @Override
    public void changeToReleaseSuccessState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from finished to release success!");

    }

    @Override
    public void changeToReviewedState() throws ChangeSprintStateException {
        if (this.sprint.getReport() != null){
            this.sprint.setState(new ReviewedState(this.sprint));
        } else {
            throw new ChangeSprintStateException("Can't change from finished to reviewed without submitting a report!");
        }
    }
}