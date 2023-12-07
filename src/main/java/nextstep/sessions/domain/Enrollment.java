package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private static final SessionState INIT_STATE = SessionState.PREPARE;
    private SessionState state;
    private List<Long> studentIds;
    private EnrollmentCondition enrollmentCondition;

    public Enrollment(EnrollmentCondition enrollmentCondition) {
        this.state = INIT_STATE;
        this.studentIds = new ArrayList<>();
        this.enrollmentCondition = enrollmentCondition;
    }

    public void changeState(SessionState state) {
        this.state = state;
    }

    public void enroll(NsUser user, int payMoney) {
        isEnrollmentPossible();
        enrollmentCondition.enrollment(payMoney);
        this.studentIds.add(user.getId());
    }

    private void isEnrollmentPossible() {
        if (!state.equals(SessionState.RECRUIT)) {
            throw new IllegalArgumentException("강의가 모집중이 아닙니다.");
        }
    }
}
