package nextstep.courses.domain;

import java.util.Set;

public class Students {
    private final Set<Long> studentIds;

    public Students(Set<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public void add(Long studentId) {
        validateToAdd(studentId);

        studentIds.add(studentId);
    }

    public int size() {
        return studentIds.size();
    }

    public void validateToAdd(Long studentId) {
        if (studentIds.contains(studentId)) {
            throw new IllegalArgumentException("이미 등록된 학생입니다.");
        }
    }
}

