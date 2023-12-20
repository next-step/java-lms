package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private final SessionState sessionState;
    private final int capacity;

    private final Students students;

    public Enrollment(SessionState sessionState, int capacity) {
        this(sessionState, capacity, new Students());

    }

    public Enrollment(SessionState sessionState, int capacity, Students students) {
        this.sessionState = sessionState;
        this.capacity = capacity;
        this.students = students;
    }

    public void enroll(NsUser loginUser) {
        if (!this.sessionState.isRecruiting()) {
            throw new IllegalArgumentException("수강신청은 모집중에만 가능합니다");
        }
        this.students.add(loginUser, capacity);
    }
}
