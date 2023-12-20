package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private final SessionState sessionState;
    private final Students students;

    public Enrollment(SessionState sessionState, int capacity) {
        this(sessionState, new Students(capacity));

    }

    public Enrollment(SessionState sessionState, Students students) {
        this.sessionState = sessionState;
        this.students = students;
    }

    public void enroll(NsUser loginUser) {
        if (!this.sessionState.isRecruiting()) {
            throw new IllegalArgumentException("수강신청은 모집중에만 가능합니다");
        }
        this.students.add(loginUser);
    }
}
