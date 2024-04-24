package nextstep.courses.domain.session.enrollment.state;

public class SessionState {

    private final RecruitmentState recruitmentState;

    public SessionState(RecruitmentState recruitmentState) {
        this.recruitmentState = recruitmentState;
    }

    public boolean isRecruitmentOpen() {
        return recruitmentState == RecruitmentState.RECRUITING;
    }
}
