package nextstep.courses.domain.student;

import java.time.LocalDateTime;
import nextstep.courses.entity.BaseEntity;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Student extends BaseEntity {

    private final StudentName studentName;

    private final Email email;

    private final Money paymentAmount;

    public Student(NsUser nsUser, Payment payment) {
        this(new StudentName(nsUser.getName()), new Email(nsUser.getEmail()), new Money(
            payment.getAmount()));
    }

    public Student(StudentName studentName, Email email, Money paymentAmount) {
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
    }

    public Student(StudentName studentName,
        Email email, Money paymentAmount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
    }

    public String getStudentName() {
        return studentName.getValue();
    }

    public String  getEmail() {
        return email.getValue();
    }

    public int getPaymentAmount() {
        return paymentAmount.getValue();
    }
}
