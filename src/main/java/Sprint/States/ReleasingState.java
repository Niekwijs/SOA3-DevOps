package sprint.states;

import sprint.Sprint;
import exceptions.ChangeSprintStateException;

public class ReleasingState implements ISprintState {

    private final Sprint sprint;
    private final String releasingState = "Can't change state while releasing!";

    public ReleasingState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void changeToInitialState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releasingState);
    }

    @Override
    public void changeToInProgressState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releasingState);
    }

    @Override
    public void changeToFinishedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releasingState);
    }

    @Override
    public void changeToReleasingState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releasingState);
    }

    @Override
    public void changeToReleaseCancelledState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releasingState);
    }

    @Override
    public void changeToReleaseErrorState() throws InterruptedException {
        this.sprint.getPipeLineManager().executePipeLineByName(this.sprint.getPipeline().getPipeLineName());
        this.sprint.notifySpecificSubscribers("ScrumMaster", "The sprint pipeline has failed");
        this.sprint.setState(new ReleaseErrorState(this.sprint));
    }

    @Override
    public void changeToReleaseSuccessState() throws InterruptedException {
        this.sprint.getPipeLineManager().executePipeLineByName(this.sprint.getPipeline().getPipeLineName());
        this.sprint.notifySpecificSubscribers("ProductOwner", "The sprint pipeline is executed successfully");
        this.sprint.notifySpecificSubscribers("ScrumMaster", "The sprint pipeline is executed successfully");
        this.sprint.setState(new ReleaseSuccessState(this.sprint));
    }

    @Override
    public void changeToReviewedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException(releasingState);
    }
}
