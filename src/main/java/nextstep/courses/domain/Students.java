package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public boolean isGreaterEqualThan(Long capacity) {
        return this.students.size() >= capacity;
    }

    public void add(Student student) {
        if (this.contains(student)) {
            throw new IllegalStateException("이미 강의를 신청하였습니다.");
        }
        this.students.add(student);
    }

    private boolean contains(Student student) {
        return this.students.stream()
                .anyMatch(currentStudent -> currentStudent.equals(student));
    }
}
