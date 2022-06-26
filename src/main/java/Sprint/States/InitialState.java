package sprint.states;

import sprint.*;
import exceptions.ChangeSprintStateException;

public class InitialState implements ISprintState {

    private Sprint sprint;

    public InitialState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void changeToInitialState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from initial to inital!");
    }

    @Override
    public void changeToInProgressState() throws ChangeSprintStateException {
        if(this.sprint.getType() == SprintType.release && this.sprint.getPipeline() == null){
            throw new ChangeSprintStateException("A release sprint needs a pipeline!!");
        } else {
            this.sprint.setState(new InProgressState(this.sprint));
        }
    }

    @Override
    public void changeToFinishedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from initial to finished!");
    }

    @Override
    public void changeToReleasingState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from initial to releasing!");
    }

    @Override
    public void changeToReleaseCancelledState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from initial to release cancelled!");
    }

    @Override
    public void changeToReleaseErrorState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from initial to release error!");
    }

    @Override
    public void changeToReleaseSuccessState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from initial to release success!");
    }

    @Override
    public void changeToReviewedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from initial to reviewed!");
    }

}
