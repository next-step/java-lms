package nextstep.courses.domain.session;

import java.time.LocalDate;

import nextstep.courses.CannotEnrollException;
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
    private SessionStatus status;
    private final SessionMetadata metadata;
    private final Enrollment enrollment;

    private Session(Long id, SessionStatus status, EnrollmentPolicy enrollmentPolicy, Period period,
        CoverImage coverImage) {
        this.id = id;
        this.status = status;
        this.metadata = new SessionMetadata(period, coverImage);
        this.enrollment = new Enrollment(enrollmentPolicy);
    }

    public static Session createFreeSession(Long id, Period period, CoverImage coverImage) {
        return new Session(id, SessionStatus.PREPARING, new FreeEnrollmentPolicy(), period, coverImage);
    }

    public static Session createPaidSession(Long id, Period period, CoverImage coverImage, Amount price,
        long capacity) {
        return new Session(id, SessionStatus.PREPARING, new PaidEnrollmentPolicy(price, capacity), period, coverImage);
    }

    /* 기능 */
    public void open() {
        this.status = SessionStatus.OPEN;
    }

    public void close() {
        this.status = SessionStatus.CLOSED;
    }

    /* ------------ 정책 검증 ------------ */
    // 수강신청이 가능한 "상태"인지 체크 (모집중 + 좌석 여유)
    public boolean canEnroll() {
        if (!status.isOpen()) {
            return false;
        }
        return enrollment.hasCapacity();
    }

    // 실제 수강신청
    public void enroll(Payment payment) {
        if (!status.isOpen()) {
            throw new CannotEnrollException("강의가 모집중이 아닙니다");
        }
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
