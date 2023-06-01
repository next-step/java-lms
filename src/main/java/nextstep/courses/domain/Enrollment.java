package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Enrollment {

    private final int maxEnrollment;
    private final Students students;

    public Enrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
        this.students = new Students(maxEnrollment);
    }

    public int maxEnrollmentValue() {
        return maxEnrollment;
    }

    public int currentEnrolmentCount() {
        return students.countEnrollment();
    }

    public boolean hasEnrolledStudent() {
        return !students.isEmpty();
    }

    public void enroll(NsUser nsUser) {
        students.enroll(nsUser);
    }

    public boolean isNotExceededMaxEnrollment(Students students) {
        return maxEnrollment >= students.countEnrollment();
    }
}
