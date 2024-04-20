package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.SessionStatus.OPEN;

import java.time.LocalDate;
import java.util.Objects;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.strategy.SessionStrategy;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class Session {

    private final Name name;
    private final SessionStatus status;
    private final Schedule schedule;
    private final SessionStrategy sessionStrategy;
    private final Long id;
    private CoverImage coverImage;
    private Course course;
    private EnrollmentCount currentEnrollmentCount;

    public Session(
            final Name name,
            final SessionStatus status,
            final Schedule schedule,
            final SessionStrategy sessionStrategy,
            final EnrollmentCount currentEnrollmentCount
    ) {
        this(null, name, status, schedule, sessionStrategy, currentEnrollmentCount);
    }

    public Session(
            final Long id,
            final Name name,
            final SessionStatus status,
            final Schedule schedule,
            final SessionStrategy sessionStrategy,
            final EnrollmentCount currentEnrollmentCount
    ) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.schedule = schedule;
        this.sessionStrategy = sessionStrategy;
        this.currentEnrollmentCount = currentEnrollmentCount;
    }

    public Long id() {
        return this.id;
    }

    public String name() {
        return this.name.value();
    }

    public String statusName() {
        return this.status.statusName();
    }

    public LocalDate startDate() {
        return this.schedule.startDate();
    }

    public LocalDate endDate() {
        return this.schedule.endDate();
    }

    public String strategyName() {
        return this.sessionStrategy.name();
    }

    public int fee() {
        return this.sessionStrategy.fee();
    }

    public int enrollmentLimit() {
        return this.sessionStrategy.enrollmentLimit();
    }

    public int currentEnrollmentCount() {
        return this.currentEnrollmentCount.value();
    }

    public CoverImage coverImage() {
        return this.coverImage;
    }

    public Course course() {
        return this.course;
    }

    public void assignCoverImage(final CoverImage coverImage) {
        this.coverImage = coverImage;
    }

    public void assignCourse(final Course course) {
        this.course = course;
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

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                this.status == that.status &&
                Objects.equals(this.schedule, that.schedule) &&
                Objects.equals(this.course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.status, this.schedule, this.course);
    }

    @Override
    public String toString() {
        return this.name.value();
    }
}
