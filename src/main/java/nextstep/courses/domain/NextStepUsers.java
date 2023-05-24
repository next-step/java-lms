package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class NextStepUsers {
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
            throw new IllegalArgumentException("강의 최대 수강 인원이 초과되었습니다.");
        }
    }

    public int enrollmentCount() {
        return this.nextStepUsers.size();
    }
}
