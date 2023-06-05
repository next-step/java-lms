package nextstep.courses.domain.registration;

import java.util.HashSet;
import java.util.Set;

public class Students {
    private final int maxUserCount;
    private final Set<Student> students;

    public Students() {
        this(0);
    }

    public Students(int maxUserCount) {
        this(maxUserCount, new HashSet<>());
    }

    public Students(int maxUserCount, Set<Student> students) {
        this.maxUserCount = maxUserCount;
        this.students = students;
    }

    public void enroll(Student student) {
        validateUserCount();
        validateUserDuplicated(student);
        students.add(student);
    }

    private void validateUserCount() {
        if (this.maxUserCount <= students.size()) {
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

    public int getMaxUserCount() {
        return this.maxUserCount;
    }
}
