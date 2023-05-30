package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Enrollment {

    private final int maxEnrollment;
    private final Students students;

    public Enrollment(int maxEnrollment, Students students) {
        this.maxEnrollment = maxEnrollment;
        this.students = students;
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
        if (isNotExceededMaxEnrollment(students)) {
            students.addStudent(nsUser);
        }
    }

    public boolean isNotExceededMaxEnrollment(Students students) {
        return maxEnrollment >= students.countEnrollment();
    }
}
