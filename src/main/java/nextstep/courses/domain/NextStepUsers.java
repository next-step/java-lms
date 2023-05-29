package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class NextStepUsers {
    public static final String MAXIMUM_ENROLLMENT_MESSAGE = "강의 최대 수강 인원이 초과되었습니다.";

    private final List<SessionUser> sessionUsers = new ArrayList<>();
    private final int maximumUserCount;

    public NextStepUsers(int maximumUserCount) {
        this.maximumUserCount = maximumUserCount;
    }

    public void enroll(NsUser nextStepUser) {
        enroll(new SessionUser(nextStepUser));
    }

    public void enroll(SessionUser sessionUser) {
        validateMaximumUserCount();
        sessionUsers.add(sessionUser);
    }

    public void cancel(SessionUser sessionUser) {
        if (sessionUser.isApproved()) {
            throw new IllegalArgumentException("수강 신청이 승인된 사용자입니다.");
        }
        sessionUsers.remove(sessionUser);
    }

    public void validateMaximumUserCount() {
        if (enrollmentCount() >= maximumUserCount) {
            throw new IllegalArgumentException(MAXIMUM_ENROLLMENT_MESSAGE);
        }
    }

    public int enrollmentCount() {
        return (int) sessionUsers.stream()
                .filter(SessionUser::isApproved)
                .count();
    }

    public int getMaximumUserCount() {
        return this.maximumUserCount;
    }
}
