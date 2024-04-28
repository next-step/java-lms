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

    private final ApprovalState approvalState;

    private final StudentType studentType;

    public Student(NsUser nsUser, Payment payment) {
        this(new StudentName(nsUser.getName()), new Email(nsUser.getEmail()), new Money(
            payment.getAmount()), ApprovalState.NON_APPROVAL, nsUser.getStudentType());
    }

    public Student(StudentName studentName, Email email, Money paymentAmount,
        ApprovalState approvalState, StudentType studentType) {
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
        this.approvalState = approvalState;
        this.studentType = studentType;
    }

    public Student(StudentName studentName, Email email, Money paymentAmount,
        ApprovalState approvalState, StudentType studentType, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
        this.approvalState = approvalState;
        this.studentType = studentType;
    }

    public String getStudentName() {
        return studentName.getValue();
    }

    public String getEmail() {
        return email.getValue();
    }

    public int getPaymentAmount() {
        return paymentAmount.getValue();
    }

    public String getApprovalState() {
        return approvalState.getValue();
    }

    public String getStudentType() {
        return studentType.getValue();
    }
}
