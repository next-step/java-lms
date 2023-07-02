package nextstep.courses.domain;

import nextstep.courses.exception.EnrollFullException;
import nextstep.users.domain.NsUser;

public class SessionStatus {
    private int maxCapacity;
    private Enrollments enrollments = new Enrollments();

    public SessionStatus(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getEnrollmentSize() {
        return enrollments.getSize();
    }

    public void  enroll(NsUser student, long sessionId) {
        if (enrollments.getSize() >= maxCapacity) {
            throw new EnrollFullException("최대 수강 인원을 초과하여 신청이 불가합니다.");
        }

        enrollments.enroll(student, sessionId);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
