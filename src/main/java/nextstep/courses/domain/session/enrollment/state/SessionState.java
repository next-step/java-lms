package nextstep.courses.domain.session.enrollment.state;

public class SessionState {

    private final ProgressState progressState;

    private final RecruitmentState recruitmentState;

    public SessionState(ProgressState progressState, RecruitmentState recruitmentState) {
        this.progressState = progressState;
        this.recruitmentState = recruitmentState;
    }

    public boolean isOnGoing() {
        return progressState == ProgressState.ONGOING;
    }

    public static RecruitmentState valueOfRecruitmentState(String value) {
        return RecruitmentState.valueOf(value);
    }

    public static ProgressState valueOfProgressState(String value) {
        return ProgressState.valueOf(value);
    }

    public String getRecruitmentState() {
        return progressState.getValue();
    }

    public String getProgressState() {
        return progressState.getValue();
    }
}
