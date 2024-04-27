package nextstep.courses.domain.session.enrollment.state;

public class SessionState {

    private final ProgressState progressState;

    public SessionState(ProgressState progressState) {
        this.progressState = progressState;
    }

    public boolean isOnGoing() {
        return progressState == ProgressState.ONGOING;
    }

    public static ProgressState valueOfRecruitmentState(String value) {
        return ProgressState.valueOf(value);
    }

    public String getRecruitmentState() {
        return progressState.getValue();
    }
}
