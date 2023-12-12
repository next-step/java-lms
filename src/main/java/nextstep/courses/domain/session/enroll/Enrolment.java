package nextstep.courses.domain.session.enroll;

import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import nextstep.courses.dto.EnrolmentInfo;

import static nextstep.courses.domain.session.enroll.EnrollStatus.*;

public class Enrolment {

    private Long id;
    private Students students;
    private EnrollStatus enrollStatus;

    public Enrolment(Students students, EnrollStatus enrollStatus) {
        this.students = students;
        this.enrollStatus = enrollStatus;
    }

    public Enrolment(Long id, Students students, EnrollStatus enrollStatus) {
        this(students, enrollStatus);
        this.id = id;
    }

    public Student enroll(EnrolmentInfo enrolmentInfo) {
        validateEnrollStatus();

        Student student = new Student(enrolmentInfo.getSessionId(), enrolmentInfo.getNsUserId());
        students.add(student);

        return student;
    }

    private void validateEnrollStatus() {
        if (isEnrollOff(enrollStatus)) {
            throw new IllegalArgumentException("해당 강의는 현재 모집중이 아닙니다.");
        }
    }
}
