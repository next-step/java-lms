package nextstep.courses.domain.session;

import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.recruit.RecruitmentStatus;

public class Enrollment {
    private final Long sessionId;
    private final SessionPolicy policy;
    private final Registrations registrations;
    private RecruitmentStatus recruitmentStatus;

    public Enrollment(Long sessionId, RecruitmentStatus recruitmentStatus, SessionPolicy policy, Registrations registrations) {
        validateRecruitmentStatus(recruitmentStatus);
        this.sessionId = sessionId;
        this.recruitmentStatus = recruitmentStatus;
        this.policy = policy;
        this.registrations = registrations;
    }

    public Registration enroll(long payAmount, long userId) {
        validateRecruitmentStatus(recruitmentStatus);

        if (registrations.isAlreadyRegistered(userId)) {
            throw new IllegalArgumentException("이미 수강신청한 학생입니다.");
        }

        policy.validate(payAmount, registrations);

        return new Registration(sessionId, userId);
    }

    public void startRecruitment() {
        this.recruitmentStatus = recruitmentStatus.open();
    }

    public void stopRecruitment() {
        this.recruitmentStatus = recruitmentStatus.close();
    }

    public Registration approve(Registration registration) {
        if (policy.getApprovalPolicy().canAutoApprove()) {
            registration.approve();
            return registration;
        }
        return null;
    }

    private void validateRecruitmentStatus(RecruitmentStatus status) {
        if (!status.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강신청이 가능합니다.");
        }
    }
}