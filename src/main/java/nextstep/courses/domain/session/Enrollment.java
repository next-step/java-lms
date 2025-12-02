package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.util.HashSet;
import java.util.Set;

public class Enrollment {
    private final SessionStatus status;
    private final SessionType sessionType;
    private final Set<Long> enrolledStudentIds;

    public Enrollment(SessionStatus status, SessionType sessionType) {
        this(status, sessionType, new HashSet<>());
    }

    public Enrollment(SessionStatus status, SessionType sessionType, Set<Long> enrolledStudentIds) {
        this.status = status;
        this.sessionType = sessionType;
        this.enrolledStudentIds = enrolledStudentIds;
    }

    public void enroll(Long studentId) {
        enroll(studentId, null);
    }

    public void enroll(Long studentId, Payment payment) {
        if (!status.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청할 수 있다");
        }
        if (!sessionType.isValidPayment(payment)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }

        if (sessionType.isOverCapacity(enrolledStudentIds.size())) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
        }
        enrolledStudentIds.add(studentId);
    }

    public boolean isEnrolled(Long studentId) {
        return enrolledStudentIds.contains(studentId);
    }
}
