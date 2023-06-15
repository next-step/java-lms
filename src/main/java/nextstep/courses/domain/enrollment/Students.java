package nextstep.courses.domain.enrollment;

import java.util.HashSet;
import java.util.Set;

public class Students {

    private final int capacity;
    private final Set<Student> students;

    public Students() {
        this(0);
    }

    public Students(int capacity) {
        this(capacity, new HashSet<>());
    }

    public Students(int capacity, Set<Student> students) {
        this.capacity = capacity;
        this.students = students;
    }

    public void enroll(Student student) {
        validateUserCount();
        validateUserDuplicated(student);
        students.add(student);
    }

    private void validateUserCount() {
        if (this.capacity <= students.size()) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }
    }

    private void validateUserDuplicated(Student student) {
        if (!students.add(student)) {
            throw new IllegalArgumentException("이미 등록 되었습니다.");
        }
    }

    public Set<Student> getUsers() {
        return this.students;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
