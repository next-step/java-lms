package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {

    private SessionStatus status;
    private SessionType type;
    private Money price;
    private Capacity maxCapacity;
    private Students students;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionCoverImage coverImage;

    private Session(SessionStatus status, SessionType type, Money price, Capacity maxCapacity, Students students, LocalDate startDate, LocalDate endDate, SessionCoverImage coverImage) {
        this.status = status;
        this.type = type;
        this.price = price;
        this.maxCapacity = maxCapacity;
        this.students = students;
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
                new Students(),
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
                new Students(),
                startDate,
                endDate,
                SessionCoverImage.EMPTY
        );
    }

    public void enroll(NsUser student, Payment payment) {
        validateRecruiting();
        validateMaxCapacity();
        validatePayment(payment);

        students.add(student);
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
        if (type == SessionType.PAID && maxCapacity.isFull(students.count())) {
            throw new IllegalArgumentException("최대 수강 인원(" + maxCapacity.value() + "명)에 도달하여 수강 신청이 불가능합니다.");
        }
    }

    private void validatePayment(Payment payment) {
        if (payment.notMatches(price.amount())) {
            throw new IllegalArgumentException("결제한 금액과 수강료가 일치하지 않습니다.");
        }
    }
}
