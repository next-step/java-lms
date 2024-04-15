package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.SessionStatus.OPEN;

import java.util.Objects;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.strategy.SessionStrategy;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class Session {

    private final Long sessionId;
    private final Name name;
    private final SessionStatus status;
    private final Schedule schedule;
    private final CoverImage coverImage;
    private final SessionStrategy sessionStrategy;
    private final Course course;
    private EnrollmentCount currentEnrollmentCount;

    public Session(
            final Long sessionId,
            final Name name,
            final SessionStatus status,
            final Schedule schedule,
            final CoverImage coverImage,
            final SessionStrategy sessionStrategy,
            final Course course,
            final EnrollmentCount currentEnrollmentCount
    ) {
        this.sessionId = sessionId;
        this.name = name;
        this.status = status;
        this.schedule = schedule;
        this.coverImage = coverImage;
        this.sessionStrategy = sessionStrategy;
        this.course = course;
        this.currentEnrollmentCount = currentEnrollmentCount;
    }

    public int currentEnrollmentCount() {
        return this.currentEnrollmentCount.value();
    }

    public void enroll(final Payment payment) {
        validateSessionStatusIsOpen();
        validateEqualSessionFeeAndPaymentAmount(payment.amount());
        validateSessionCurrentEnrollmentCountIsLessThanLimit();

        this.currentEnrollmentCount = currentEnrollmentCount.increase();
    }

    private void validateSessionStatusIsOpen() {
        if (this.status != OPEN) {
            throw new IllegalStateException("모집 중인 강의에만 수강 신청할 수 있습니다. 현재 상태: " + this.status);
        }
    }

    private void validateEqualSessionFeeAndPaymentAmount(final Money paymentAmount) {
        if (!sessionStrategy.isPaymentSufficient(paymentAmount)) {
            throw new IllegalArgumentException("결제 금액과 수강료가 일치하지 않습니다. 결제 금액: " + paymentAmount);
        }
    }

    private void validateSessionCurrentEnrollmentCountIsLessThanLimit() {
        if (!sessionStrategy.canEnrollMoreStudents(this.currentEnrollmentCount)) {
            throw new IllegalStateException("현재 수강 인원이 가득차서 더이상 수강 신청할 수 없습니다. 현재 인원: " + this.currentEnrollmentCount);
        }
    }

    @Override
    public boolean equals(final Object otherSession) {
        if (this == otherSession) {
            return true;
        }

        if (otherSession == null || getClass() != otherSession.getClass()) {
            return false;
        }

        final Session that = (Session)otherSession;

        return Objects.equals(this.sessionId, that.sessionId) &&
                Objects.equals(this.name, that.name) &&
                this.status == that.status &&
                Objects.equals(this.schedule, that.schedule) &&
                Objects.equals(this.course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sessionId, this.name, this.status, this.schedule, this.course);
    }
}
