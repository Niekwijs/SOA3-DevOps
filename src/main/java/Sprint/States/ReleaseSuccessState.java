package sprint.states;

import sprint.Sprint;
import exceptions.ChangeSprintStateException;

public class ReleaseSuccessState implements ISprintState {

    private final Sprint sprint;
    private final String releaseSuccess = "Release success is a final state!";

    public ReleaseSuccessState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void changeToInitialState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }

    @Override
    public void changeToInProgressState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }

    @Override
    public void changeToFinishedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }

    @Override
    public void changeToReleasingState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }

    @Override
    public void changeToReleaseCancelledState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }

    @Override
    public void changeToReleaseErrorState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }

    @Override
    public void changeToReleaseSuccessState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }

    @Override
    public void changeToReviewedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releaseSuccess);
    }
}
