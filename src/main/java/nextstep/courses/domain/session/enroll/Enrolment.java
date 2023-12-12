package nextstep.courses.domain.session.enroll;

import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import nextstep.courses.dto.EnrolmentInfo;

import static nextstep.courses.domain.session.enroll.RecruitingStatus.*;
import static nextstep.courses.domain.session.student.SelectionStatus.*;

public class Enrolment {

    private Long id;
    private Students students;
    private RecruitingStatus recruitingStatus;

    public Enrolment(Students students, RecruitingStatus recruitingStatus) {
        this.students = students;
        this.recruitingStatus = recruitingStatus;
    }

    public Enrolment(Long id, Students students, RecruitingStatus recruitingStatus) {
        this(students, recruitingStatus);
        this.id = id;
    }

    public Student enroll(EnrolmentInfo enrolmentInfo) {
        validateRecruitingStatus();

        Student student = new Student(enrolmentInfo.getSessionId(), enrolmentInfo.getNsUserId(), WAITING);
        students.add(student);

        return student;
    }

    private void validateRecruitingStatus() {
        if (isNotRecruiting(recruitingStatus)) {
            throw new IllegalArgumentException("해당 강의는 현재 모집중이 아닙니다.");
        }
    }

    public Student select(Student student, SelectionStatus selectionStatus) {
        return students.selectStudents(student, selectionStatus);
    }

    public int studentsSize() {
        return students.size();
    }
}
