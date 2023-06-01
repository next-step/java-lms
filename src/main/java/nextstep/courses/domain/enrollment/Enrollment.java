package nextstep.courses.domain.enrollment;

import nextstep.users.domain.NsUser;

public class Enrollment {

    private final Students students;

    public Enrollment(int maxEnrollment) {
        this.students = new Students(maxEnrollment);
    }

    public int sessionCapacity() {
        return students.sessionCapacity();
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
}
