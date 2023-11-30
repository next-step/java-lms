package nextstep.courses.domain;

import nextstep.courses.domain.type.Price;
import nextstep.courses.exception.DifferentSessionAmountException;
import nextstep.courses.exception.ExceedMaxStudentException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChargedSession extends Session {

    private final int maxNumberOfStudent;
    private final Price price;

    public ChargedSession(Duration duration, Images images, int maxNumberOfStudent, BigDecimal price) {
        this(0L, duration, images, duration.sessionStatus(LocalDate.now()), maxNumberOfStudent, price, LocalDateTime.now(), null);
    }

    public ChargedSession(Duration duration, Images images, int maxNumberOfStudent, BigDecimal price, LocalDateTime createdAt) {
        this(0L, duration, images, duration.sessionStatus(createdAt.toLocalDate()), maxNumberOfStudent, price, createdAt, null);
    }

    public ChargedSession(Duration duration, Images images, int maxNumberOfStudent, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, duration, images, duration.sessionStatus(createdAt.toLocalDate()), maxNumberOfStudent, price, createdAt, updatedAt);
    }

    public ChargedSession(Duration duration, Images images, SessionStatus status, int maxNumberOfStudent, BigDecimal price) {
        this(0L, duration, images, status, maxNumberOfStudent, price, LocalDateTime.now(), null);
    }

    public ChargedSession(Duration duration, Images images, SessionStatus status, int maxNumberOfStudent, BigDecimal price, LocalDateTime createdAt) {
        this(0L, duration, images, status, maxNumberOfStudent, price, createdAt, null);
    }

    public ChargedSession(Duration duration, Images images, SessionStatus status, int maxNumberOfStudent, BigDecimal price,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, duration, images, status, maxNumberOfStudent, price, createdAt, updatedAt);
    }

    public ChargedSession(Long id, Duration duration, Images images, SessionStatus status, int maxNumberOfStudent, BigDecimal price,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, duration, images, status, createdAt, updatedAt);
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.price = new Price(price);
    }

    public void apply(Payment payment, NsUser nsUser, LocalDateTime applyAt) {
        validate(payment);
        addStudent(nsUser, applyAt);
    }

    private void validate(Payment payment) {
        validateStatus();
        validateAmount(payment);
        validateMaxNumberOfStudent();
    }

    private void validateAmount(Payment payment) {
        if (!payment.isEqualAmount(this.price)) {
            throw new DifferentSessionAmountException("수강료와 결제 금액이 일치하지 않습니다.");
        }
    }

    private void validateMaxNumberOfStudent() {
        if (!this.applies.isApprovalCountLessThan(this.maxNumberOfStudent)) {
            throw new ExceedMaxStudentException("수강 인원을 초과했습니다.");
        }
    }

    public int maxNumberOfStudent() {
        return this.maxNumberOfStudent;
    }

    public Price price() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargedSession)) return false;
        if (!super.equals(o)) return false;
        ChargedSession session = (ChargedSession) o;
        return maxNumberOfStudent == session.maxNumberOfStudent && Objects.equals(price, session.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxNumberOfStudent, price);
    }

    @Override
    public String toString() {
        return super.toString() +
            ", ChargedSession{" +
            "maxNumberOfStudent=" + maxNumberOfStudent +
            ", price=" + price +
            '}';
    }
}
