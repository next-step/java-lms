package nextstep.courses.domain;

import nextstep.users.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    public static final String MAX_CAPACITY_ERROR_MESSAGE = "강의 최대 수강 인원을 초과하였습니다.";
    public static final String ENROLLMENT_ERROR_MESSAGE = "수강 신청 상태가 아닙니다.";
    private final SessionStatus sessionStatus;
    private final int capacity;
    private final List<User> students;

    public Enrollment(SessionStatus sessionStatus, int capacity) {
        this(sessionStatus, capacity, new ArrayList<>());
    }

    public Enrollment(SessionStatus sessionStatus, int capacity, List<User> students) {
        if (capacity < students.size()){
            throw new IllegalArgumentException(MAX_CAPACITY_ERROR_MESSAGE);
        }
        this.sessionStatus = sessionStatus;
        this.capacity = capacity;
        this.students = students;
    }

    public void enroll(User student){
        if (!sessionStatus.isEnrolling()){
            throw new IllegalArgumentException(ENROLLMENT_ERROR_MESSAGE);
        }

        if (isFullCapacity()) {
            throw new IllegalArgumentException(MAX_CAPACITY_ERROR_MESSAGE);
        }
        students.add(student);
    }

    private boolean isFullCapacity() {
        return students.size() == capacity;
    }
}