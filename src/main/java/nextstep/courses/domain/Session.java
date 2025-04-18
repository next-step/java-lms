package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {
    private Long id;
    private Course course;
    private SessionStatus status;
    private SessionType type;
    private Money price;
    private Capacity maxCapacity;
    private Enrollments enrollments;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionCoverImage coverImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(SessionStatus status, SessionType type, Money price, Capacity maxCapacity, Enrollments enrollments, LocalDate startDate, LocalDate endDate, SessionCoverImage coverImage) {
        this.status = status;
        this.type = type;
        this.price = price;
        this.maxCapacity = maxCapacity;
        this.enrollments = enrollments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
    }

    public static Session createFreeSession(LocalDate startDate, LocalDate endDate) {
        return new Session(
                SessionStatus.READY,
                SessionType.FREE,
                Money.FREE,
                Capacity.ZERO,
                new Enrollments(new ArrayList<>()),
                startDate,
                endDate,
                SessionCoverImage.EMPTY
        );
    }

    public static Session createPaidSession(Money price, Capacity maxCapacity, LocalDate startDate, LocalDate endDate) {
        return new Session(
                SessionStatus.READY,
                SessionType.PAID,
                price,
                maxCapacity,
                new Enrollments(new ArrayList<>()),
                startDate,
                endDate,
                SessionCoverImage.EMPTY
        );
    }

    public Enrollment enroll(Student student, Payment payment) {
        validateRecruiting();
        validateMaxCapacity();
        validatePayment(payment);

        Enrollment enrollment = new Enrollment(this, student);
        enrollments.add(enrollment);
        student.addEnrollment(enrollment);

        return enrollment;
    }

    public void updateCoverImage(SessionCoverImage newCoverImage) {
        coverImage = newCoverImage;
    }

    public void ready() {
        status = SessionStatus.READY;
    }

    public void startRecruiting() {
        status = SessionStatus.RECRUITING;
    }

    public void close() {
        status = SessionStatus.CLOSED;
    }

    private void validateRecruiting() {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("본 강의는 수강생을 모집하고 있지 않습니다.");
        }
    }

    private void validateMaxCapacity() {
        if (type == SessionType.PAID && maxCapacity.isFull(enrollments.count())) {
            throw new IllegalArgumentException("최대 수강 인원(" + maxCapacity.value() + "명)에 도달하여 수강 신청이 불가능합니다.");
        }
    }

    private void validatePayment(Payment payment) {
        if (payment.notMatches(price.amount())) {
            throw new IllegalArgumentException("결제한 금액과 수강료가 일치하지 않습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public SessionType getType() {
        return type;
    }

    public Money getPrice() {
        return price;
    }

    public Capacity getMaxCapacity() {
        return maxCapacity;
    }

    public Enrollments getEnrollments() {
        return enrollments;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
