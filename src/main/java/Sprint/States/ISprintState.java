package sprint.states;

import exceptions.ChangeSprintStateException;

public interface ISprintState {
    void changeToInitialState() throws ChangeSprintStateException;
    void changeToInProgressState() throws ChangeSprintStateException;
    void changeToFinishedState() throws ChangeSprintStateException;
    void changeToReleasingState() throws ChangeSprintStateException;
    void changeToReleaseCancelledState() throws ChangeSprintStateException;
    void changeToReleaseErrorState() throws ChangeSprintStateException, InterruptedException;
    void changeToReleaseSuccessState() throws ChangeSprintStateException, InterruptedException;
    void changeToReviewedState() throws ChangeSprintStateException;
}
