package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class SessionEnrollment {

    private final SessionStatus sessionStatus;
    private final Set<Student> students = new HashSet<>();
    private final Long maxNumberOfStudent;

    public SessionEnrollment(SessionStatus sessionStatus, Long maxNumberOfStudent) {
        this.sessionStatus = sessionStatus;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public void enroll(Student student) {
        if (!sessionStatus.canJoin()) {
            throw new CannotEnrollException("현재는 수강신청을 할 수 없는 강의 상태입니다. 현재 강의 상태 = " + sessionStatus.name());
        }

        if (isPositionFull()) {
            throw new CannotEnrollException(
                    "현재 강의(Session)는 수강인원이 꽉 차서 더 이상 등록할 수 없습니다." + "최대인원 = " + maxNumberOfStudent);
        }
        this.students.add(student);
    }

    public Long totalStudentNum() {
        return Long.valueOf(students.size());
    }

    public boolean isPositionFull() {
        return totalStudentNum() == maxNumberOfStudent;
    }

}
