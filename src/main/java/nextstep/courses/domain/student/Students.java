package nextstep.courses.domain.student;

import nextstep.courses.exceptions.AlreadyEnrollmentException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Students {

    private final int capacity;

    private List<NsUser> students;

    public Students(int capacity, List<NsUser> students) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.students = students;
    }

    private void validateCapacity(final int capacity) {
        if (this.capacity < capacity) {
            throw new IllegalArgumentException("최대 수용 인원인 " + capacity + "명을 초과할 수 없습니다.");
        }
    }

    public int sizeOfStudents() {
        return students.size();
    }

    public void enroll(final NsUser student) throws AlreadyEnrollmentException {
        validateEnrollment(student);
        students.add(student);
    }

    private void validateEnrollment(final NsUser student) throws AlreadyEnrollmentException {
        if (isFullCapacity()) {
            throw new IllegalArgumentException("최대 수용 인원인 " + capacity + "명을 초과할 수 없습니다.");
        }
        if (students.contains(student)) {
            throw new AlreadyEnrollmentException(student.getName() + "는 이미 수강 신청한 학생입니다.");
        }
    }

    private boolean isFullCapacity() {
        return this.sizeOfStudents() >= capacity;
    }

}
