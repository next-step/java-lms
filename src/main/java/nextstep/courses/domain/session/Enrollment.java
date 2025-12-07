package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Enrollment {
    private final Long sessionId;
    private final SessionStatus status;
    private final SessionType sessionType;
    private final List<EnrolledStudent> enrolledStudents;

    public Enrollment(SessionStatus status, SessionType sessionType) {
        this(null, status, sessionType, Collections.emptyList());
    }

    public Enrollment(Long sessionId, SessionStatus status, SessionType sessionType, List<EnrolledStudent> enrolledStudents) {
        this.sessionId = sessionId;
        this.status = status;
        this.sessionType = sessionType;
        this.enrolledStudents = enrolledStudents;
    }

    public EnrolledStudent enroll(Long nsUserId, Payment payment) {
        if (!status.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청할 수 있다");
        }
        if (!sessionType.isValidPayment(payment)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }

        if (sessionType.isOverCapacity(enrolledStudents.size())) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
        }
        return new EnrolledStudent(sessionId,nsUserId);
    }

    public SessionStatus getStatus() {
        return status;
    }

    public SessionType getSessionType() {
        return sessionType;
    }
}
