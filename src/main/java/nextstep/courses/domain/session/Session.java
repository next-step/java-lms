package nextstep.courses.domain.session;

import java.math.BigInteger;
import java.time.LocalDate;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.InvalidPeriodException;

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
    private Enrollment enrollment;

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

    public static Session restoreSession(Long id, SessionStatus status, Period period, CoverImage coverImage,
        Amount price, long maxCapacity, int enrolledCount) {
        EnrollmentPolicy policy;
        if (price.equals(BigInteger.ZERO)) {
            policy = new FreeEnrollmentPolicy();
        } else {
            policy = new PaidEnrollmentPolicy(price, maxCapacity);
        }
        Session session = new Session(id, status, policy, period, coverImage);
        session.enrollment = new Enrollment(policy, enrolledCount);
        return session;
    }

    /* 기능 */
    public void open() {
        validateNotEnded();
        this.status = SessionStatus.OPEN;
    }

    public void close() {
        validateNotEnded();
        this.status = SessionStatus.CLOSED;
    }

    private void validateNotEnded() {
        LocalDate today = LocalDate.now();
        if (today.isAfter(endAt())) {
            throw new IllegalStateException("종료일 ( " + endAt() + " )이 지난 강의는 더이상 변경할 수 없습니다.");
        }
    }

    /* ------------ 정책 검증 ------------ */
    // 수강신청이 가능한 "상태"인지 체크 (모집중 + 좌석 여유)
    public boolean canEnroll() {
        validateNotEnded();
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
    public BigInteger price() {
        return enrollment.price().getAmount();
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

    public Long getId() {
        return id;
    }

    public CoverImage getCoverImage() {
        return metadata.getCoverImage();
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Long getMaxCapacity() {
        return enrollment.remainingSeats().orElseGet(() -> (long)0);
    }
}
