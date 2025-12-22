package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.RecruitmentStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.payments.domain.Payment;

import java.util.Collections;
import java.util.List;

public class Enrollment {
    private final Long sessionId;
    private final RecruitmentStatus recruitmentStatus;
    private final SessionType sessionType;
    private final List<EnrollmentCandidate> enrolledStudents;

    public Enrollment(RecruitmentStatus recruitmentStatus, SessionType sessionType) {
        this(null, recruitmentStatus, sessionType, Collections.emptyList());
    }


    public Enrollment(Long sessionId, RecruitmentStatus recruitmentStatus, SessionType sessionType, List<EnrollmentCandidate> enrolledStudents) {
        this.sessionId = sessionId;
        this.recruitmentStatus = recruitmentStatus;
        this.sessionType = sessionType;
        this.enrolledStudents = enrolledStudents;
    }

    public EnrollmentCandidate enroll(Long nsUserId, Payment payment) {
        if (!recruitmentStatus.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청할 수 있다");
        }
        if (!sessionType.isValidPayment(payment)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }

        if (sessionType.isOverCapacity(enrolledStudents.size())) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
        }
        return new EnrollmentCandidate(sessionId, nsUserId);
    }

    public EnrollmentCandidate apply(Long nsUserId, Payment payment) {
        if (!recruitmentStatus.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청할 수 있습니다.");
        }
        if (!sessionType.isValidPayment(payment)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
        if (sessionType.isOverCapacity(enrolledStudents.size())) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
        }
        return new EnrollmentCandidate(sessionId, nsUserId);
    }

    public EnrollmentCandidate approve(EnrollmentCandidate candidate, Long adminId) {
        candidate.approve(adminId);
        return candidate;
    }


    public SessionType getSessionType() {
        return sessionType;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }
}
