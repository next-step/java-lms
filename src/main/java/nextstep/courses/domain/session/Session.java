package nextstep.courses.domain.session;

import java.time.LocalDate;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.session.metadata.Period;
import nextstep.courses.domain.session.metadata.SessionMetadata;
import nextstep.courses.domain.session.metadata.coverImage.CoverImage;
import nextstep.payments.domain.Payment;

/**
 * 강의 엔터티 - 가변 객체 : Aggregate Root
 */
public class Session {
    private final Long id;
    private final SessionMetadata metadata;
    private final Enrollment enrollment;

    public Session(Long id, Period period) {
        this(id, period, SessionStatus.PREPARING);
    }

    public Session(Long id, Period period, SessionStatus status) {
        this(id, period, status, EnrollmentPolicy.free());
    }

    public Session(Long id, Period period, SessionStatus status, EnrollmentPolicy enrollmentPolicy) {
        this(id, period, status, enrollmentPolicy, null);
    }

    public Session(Long id, Period period, SessionStatus status, EnrollmentPolicy enrollmentPolicy,
        CoverImage coverImage) {
        this.id = id;
        this.metadata = new SessionMetadata(period, coverImage);
        this.enrollment = new Enrollment(status, enrollmentPolicy);
    }

    /* ------------ 정책 검증 ------------ */
    // 모집중 상태인지
    private boolean isOpen() {
        return enrollment.isOpen();
    }

    // 수강신청이 가능한 "상태"인지 체크 (모집중 + 좌석 여유)
    public boolean canEnroll() {
        return enrollment.canEnroll();
    }

    // 실제 수강신청
    public void enroll(Payment payment) {
        enrollment.enroll(payment);
    }

    /* ------------ 정보성 메서드 ------------ */
    public Amount price() {
        return enrollment.price();
    }

    public boolean isFree() {
        return enrollment.isFree();
    }

    public LocalDate startAt() {
        return metadata.startAt();
    }

    public LocalDate endAt() {
        return metadata.endAt();
    }
}
