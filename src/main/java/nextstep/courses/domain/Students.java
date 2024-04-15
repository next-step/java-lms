package nextstep.courses.domain;

import nextstep.users.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Students {
    public static final String ALREADY_ENROLLMENT_ERROR_MESSAGE = "이미 수강 신청했습니다.";

    private final List<User> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public void enroll(User student){
        if (students.contains(student)) {
            throw new IllegalArgumentException(ALREADY_ENROLLMENT_ERROR_MESSAGE);
        }
        students.add(student);
    }

    public boolean isOversize(int capacity){
        return capacity < students.size();
    }
    public boolean isFull(int capacity){
        return capacity == students.size();
    }
}
