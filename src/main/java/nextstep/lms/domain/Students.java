package nextstep.lms.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {
    private final List<Long> students;

    public Students(List<Long> students) {
        this.students = new ArrayList<>(students);
    }

    public int size() {
        return students.size();
    }

    public void enroll(int capacity, Long userId) {
        if (this.students.contains(userId)) {
            throw new IllegalArgumentException("이미 수강중인 강의입니다.");
        }
        if (this.students.size() >= capacity) {
            throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다.");
        }
        this.students.add(userId);
    }

    public List<Long> getStudents() {
        return Collections.unmodifiableList(students);
    }
}
