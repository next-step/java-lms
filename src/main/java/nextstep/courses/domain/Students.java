package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final int capacity;

    private final List<NsUser> students;

    public Students(int capacity) {
        this(capacity, new ArrayList<>());
    }

    public Students(int capacity, List<NsUser> students) {
        if (capacity < students.size()) {
            throw new IllegalArgumentException("최대 수용 인원인 " + capacity + "명을 초과할 수 없습니다.");
        }
        this.capacity = capacity;
        this.students = students;
    }

    public void enroll(NsUser student) throws AlreadyEnrollmentException {
        if (isFullCapacity()) {
            throw new IllegalArgumentException("최대 수용 인원인 " + capacity + "명을 초과할 수 없습니다.");
        }
        if (students.contains(student)) {
            throw new AlreadyEnrollmentException(student + "는 이미 수강 신청한 학생입니다.");
        }
        students.add(student);
    }

    private boolean isFullCapacity() {
        return this.sizeOfStudents() >= capacity;
    }

    public int sizeOfStudents() {
        return students.size();
    }

}
