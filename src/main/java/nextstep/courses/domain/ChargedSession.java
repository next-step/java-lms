package nextstep.courses.domain;

import nextstep.courses.domain.type.Price;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.exception.DifferentSessionAmountException;
import nextstep.courses.exception.ExceedMaxStudentException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.math.BigDecimal;

public class ChargedSession extends Session {

    private final int maxNumberOfStudent;
    private final Price price;

    public ChargedSession(Duration duration, Image image, int maxNumberOfStudent, BigDecimal price) {
        super(duration, image);
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.price = new Price(price);
    }

    public ChargedSession(Duration duration, Image image, SessionStatus status, int maxNumberOfStudent, BigDecimal price) {
        super(duration, image, status);
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.price = new Price(price);
    }

    public void apply(Payment payment, NsUser nsUser) {
        validate(payment);
        addStudent(nsUser);
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
        if (this.maxNumberOfStudent <= this.students.size()) {
            throw new ExceedMaxStudentException("수강 인원을 초과했습니다.");
        }
    }

}
