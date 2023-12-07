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

    public void paidSessionEnroll(int capacity, Long userId) {
        duplicationCheck(userId);
        if (this.students.size() >= capacity) {
            throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다.");
        }
        this.students.add(userId);
    }

    public void freeSessionEnroll(Long userId) {
        duplicationCheck(userId);
        this.students.add(userId);
    }

    private void duplicationCheck(Long userId) {
        if (this.students.contains(userId)) {
            throw new IllegalArgumentException("이미 수강중인 강의입니다.");
        }
    }

    public List<Long> getStudents() {
        return Collections.unmodifiableList(students);
    }
}
