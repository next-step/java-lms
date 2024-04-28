package nextstep.courses.domain.student;

import java.time.LocalDateTime;
import nextstep.courses.entity.BaseEntity;
import nextstep.courses.error.exception.AlreadyApprovedCancelException;
import nextstep.courses.error.exception.AlreadyApprovedException;
import nextstep.courses.error.exception.ApprovalNotAllowedException;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Student extends BaseEntity {

    private final Long id;

    private final StudentName studentName;

    private final Email email;

    private final Money paymentAmount;

    private final ApprovalState approvalState;

    private final StudentType studentType;

    public Student(NsUser nsUser, Payment payment) {
        this(null, new StudentName(nsUser.getName()), new Email(nsUser.getEmail()), new Money(
            payment.getAmount()), ApprovalState.NON_APPROVAL, nsUser.getStudentType());
    }

    public Student(Long id, StudentName studentName, Email email, Money paymentAmount,
        ApprovalState approvalState, StudentType studentType) {
        this.id = id;
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
        this.approvalState = approvalState;
        this.studentType = studentType;
    }

    public Student(Long id, StudentName studentName, Email email, Money paymentAmount,
        ApprovalState approvalState, StudentType studentType, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
        this.approvalState = approvalState;
        this.studentType = studentType;
    }

    public Student approveStudent() {
        if (StudentType.NORMAL == studentType) {
            throw new ApprovalNotAllowedException(studentType);
        }

        if (ApprovalState.APPROVAL == approvalState) {
            throw new AlreadyApprovedException(approvalState);
        }

        return new Student(id, studentName, email, paymentAmount, ApprovalState.APPROVAL, studentType,
            createdAt, updatedAt);
    }

    public Student approveCancelStudent() {
        if (ApprovalState.NON_APPROVAL == approvalState) {
            throw new AlreadyApprovedCancelException(approvalState);
        }

        return new Student(id, studentName, email, paymentAmount, ApprovalState.NON_APPROVAL,
            studentType, createdAt, updatedAt);
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", studentName=" + studentName +
            ", email=" + email +
            ", paymentAmount=" + paymentAmount +
            ", approvalState=" + approvalState +
            ", studentType=" + studentType +
            "} " + super.toString();
    }
}
