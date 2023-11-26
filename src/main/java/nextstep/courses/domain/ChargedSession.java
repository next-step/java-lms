package nextstep.courses.domain;

import nextstep.courses.DifferentSessionAmountException;
import nextstep.courses.ExceedMaxStudentException;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.payments.domain.Payment;

import java.math.BigDecimal;

public class ChargedSession extends Session {

    private final int maxNumberOfStudent;
    private final BigDecimal price;
    private int numberOfStudent;

    public static ChargedSession init(Long id, Duration duration, Image image, int maxNumberOfStudent, BigDecimal price) {
        return new ChargedSession(id, duration, image, SessionStatus.READY, maxNumberOfStudent, price);
    }

    private ChargedSession(Long id, Duration duration, Image image, SessionStatus status, int maxNumberOfStudent, BigDecimal price) {
        super(id, duration, image, status);
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.price = price;
    }

    public Payment apply(Long nsUserId, BigDecimal amount) {
        validateAmount(amount);
        addStudent();

        return Payment.init(this.id, nsUserId, amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (this.price.compareTo(amount) != 0) {
            throw new DifferentSessionAmountException("수강료와 결제 금액이 일치하지 않습니다.");
        }
    }

    public void addStudent() {
        validateMaxNumberOfStudent();
        this.numberOfStudent++;
    }

    private void validateMaxNumberOfStudent() {
        if (this.maxNumberOfStudent == this.numberOfStudent) {
            throw new ExceedMaxStudentException("수강 인원을 초과했습니다.");
        }
    }

}
