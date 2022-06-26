package sprint.states;

import sprint.Sprint;
import exceptions.ChangeSprintStateException;

public class ReleaseErrorState implements ISprintState {

    private final Sprint sprint;
    private final String RELEASE_ERROR = "Sprint release has thrown an error!";

    public ReleaseErrorState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void changeToInitialState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from release error to inital!");
    }

    @Override
    public void changeToInProgressState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from release error to in progress!");
    }

    @Override
    public void changeToFinishedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from release error to finished!");
    }

    @Override
    public void changeToReleasingState() {
        System.out.println("Re releasing pipeline..");
        this.sprint.setState(new ReleasingState(this.sprint));
    }

    @Override
    public void changeToReleaseCancelledState(){
        this.sprint.notifySpecificSubscribers("ScrumMaster", RELEASE_ERROR);
        this.sprint.notifySpecificSubscribers("ProductOwner", RELEASE_ERROR);
        System.out.println(RELEASE_ERROR);
        this.sprint.setState(new ReleaseCancelledState(this.sprint));
    }

    @Override
    public void changeToReleaseErrorState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from release error to release error!");
    }

    @Override
    public void changeToReleaseSuccessState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from release error to release success!");
    }

    @Override
    public void changeToReviewedState() throws ChangeSprintStateException {
        throw new ChangeSprintStateException("Can't change from release error to reviewed!");
    }
}
