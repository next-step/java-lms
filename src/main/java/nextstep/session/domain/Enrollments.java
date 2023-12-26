package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public Enrollments() {
    }

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments.addAll(enrollments);
    }

    public Enrollment add(NsUser student, Session session) {
        Enrollment enrollment = new Enrollment(student, session);
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment admiss(NsUser student, Session session) {
        Enrollment enrollment = findEnrollment(student, session);
        enrollment.admiss();
        return enrollment;
    }


    public Enrollment cancel(NsUser student, Session session) {
        Enrollment enrollment = findEnrollment(student, session);
        enrollments.remove(enrollment);
        return enrollment;
    }

    private Enrollment findEnrollment(NsUser student, Session session) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getSessionId().equals(session.getId()) && enrollment.getStudentId().equals(student.getId()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("수강 신청하지 않은 학생입니다."));
    }

    public int enrolledNumber() {
        return this.enrollments.size();
    }
}
