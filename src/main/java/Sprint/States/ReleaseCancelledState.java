package sprint.states;

import sprint.Sprint;
import exceptions.ChangeSprintStateException;

public class ReleaseCancelledState implements ISprintState {

    private Sprint sprint;
    private final String releaseCancelled = "Release cancelled is a final state!";

    public ReleaseCancelledState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void changeToInitialState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }

    @Override
    public void changeToInProgressState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }

    @Override
    public void changeToFinishedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }

    @Override
    public void changeToReleasingState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }

    @Override
    public void changeToReleaseCancelledState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }

    @Override
    public void changeToReleaseErrorState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }

    @Override
    public void changeToReleaseSuccessState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }

    @Override
    public void changeToReviewedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseCancelled);
    }
}
