package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class NextStepUsers {
    public static final String MAXIMUM_ENROLLMENT_MESSAGE = "강의 최대 수강 인원이 초과되었습니다.";

    private final List<NsUser> nextStepUsers = new ArrayList<>();
    private final int maximumUserCount;

    public NextStepUsers(int maximumUserCount) {
        this.maximumUserCount = maximumUserCount;
    }

    public void enroll(NsUser nextStepUser) {
        validateMaximumUserCount();
        nextStepUsers.add(nextStepUser);
    }

    public void validateMaximumUserCount() {
        if (nextStepUsers.size() >= maximumUserCount) {
            throw new IllegalArgumentException(MAXIMUM_ENROLLMENT_MESSAGE);
        }
    }

    public int enrollmentCount() {
        return this.nextStepUsers.size();
    }

    public int getMaximumUserCount() {
        return this.maximumUserCount;
    }
}
