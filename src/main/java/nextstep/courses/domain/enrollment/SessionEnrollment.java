package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.exception.AlreadyEnrollmentException;
import nextstep.courses.domain.exception.CannotEnrollException;

import java.util.HashSet;
import java.util.Set;

public class SessionEnrollment {

    private final SessionStatus sessionStatus;
    private final Set<Student> students = new HashSet<>();
    private final Long capacity;

    public SessionEnrollment(SessionStatus sessionStatus, Long capacity) {
        this.sessionStatus = sessionStatus;
        this.capacity = capacity;
    }

    public void enroll(Student student) {
        validateSessionStatus();
        validateUserCount();
        validateDuplicated(student);
        this.students.add(student);
    }

    public Long totalStudentNum() {
        return Long.valueOf(students.size());
    }

    public boolean isPositionFull() {
        return totalStudentNum() == capacity;
    }

    public SessionStatus getStatus() {
        return sessionStatus;
    }

    private void validateSessionStatus() {
        if (!sessionStatus.canJoin()) {
            throw new CannotEnrollException("현재는 수강신청을 할 수 없는 강의 상태입니다. 현재 강의 상태 = " + sessionStatus.name());
        }
    }

    private void validateUserCount() {
        if (isPositionFull()) {
            throw new CannotEnrollException(
                    "현재 강의(Session)는 수강인원이 꽉 차서 더 이상 등록할 수 없습니다." + "최대인원 = " + capacity);
        }
    }

    private void validateDuplicated(Student student) {
        if (students.contains(student)) {
            throw new AlreadyEnrollmentException(student + " 학생은 이미 등록한 상태입니다.");
        }
    }
}
