package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private final SessionState sessionState;
    private final int capacity;

    private final List<NsUser> students;

    public Enrollment(SessionState sessionState, int capacity) {
        this(sessionState, capacity, new ArrayList<>());

    }

    public Enrollment(SessionState sessionState, int capacity, List<NsUser> students) {
        this.sessionState = sessionState;
        this.capacity = capacity;
        this.students = students;
    }

    public void enroll(NsUser loginUser) {
        if (this.students.size() == capacity) {
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과할 수 없습니다");
        }
        this.students.add(loginUser);
    }
}
