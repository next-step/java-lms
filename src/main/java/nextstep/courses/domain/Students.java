package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final int capacity;

    private final List<Student> students;

    public Students(int capacity) {
        this(capacity, new ArrayList<>());
    }

    public Students(int capacity, List<Student> students) {
        this.capacity = capacity;
        this.students = students;
    }

    public void enroll(Student student) throws AlreadyEnrollmentException {
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
