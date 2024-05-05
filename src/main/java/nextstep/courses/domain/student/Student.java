package nextstep.courses.domain.student;

import java.time.LocalDateTime;
import nextstep.courses.entity.BaseEntity;
import nextstep.courses.error.exception.AlreadyApprovedCancelException;
import nextstep.courses.error.exception.AlreadyApprovedException;
import nextstep.courses.error.exception.ApprovalNotAllowedException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Student extends BaseEntity {

    private final Long id;

    private final StudentName studentName;

    private final Email email;

    private final ApprovalState approvalState;

    private final StudentType studentType;

    public Student(NsUser nsUser, Payment payment) {
        this(null, new StudentName(nsUser.getName()), new Email(nsUser.getEmail()), ApprovalState.NON_APPROVAL, nsUser.getStudentType());
    }

    public Student(Long id, StudentName studentName, Email email,
        ApprovalState approvalState, StudentType studentType) {
        this(id, studentName, email, approvalState, studentType, null, null);
    }

    public Student(Long id, StudentName studentName, Email email,
        ApprovalState approvalState, StudentType studentType, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.studentName = studentName;
        this.email = email;
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

        return new Student(id, studentName, email, ApprovalState.APPROVAL, studentType,
            createdAt, updatedAt);
    }

    public Student cancelStudent() {
        if (ApprovalState.NON_APPROVAL == approvalState) {
            throw new AlreadyApprovedCancelException(approvalState);
        }

        return new Student(id, studentName, email, ApprovalState.NON_APPROVAL,
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

    public String getApprovalState() {
        return approvalState.getValue();
    }

    public String getStudentType() {
        return studentType.getValue();
    }
}
