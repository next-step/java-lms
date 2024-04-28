package nextstep.courses.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.student.ApprovalState;
import nextstep.courses.domain.student.Email;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentName;
import nextstep.courses.domain.student.StudentType;
import nextstep.payments.domain.Money;

public class StudentEntity extends BaseEntity {

    private Long id;

    private String studentName;

    private String email;

    private int paymentAmount;

    private String approvalState;

    private String studentType;


    public Student toStudent() {
        return new Student(id, new StudentName(studentName), new Email(email),
            new Money(paymentAmount),
            ApprovalState.valueOf(approvalState), StudentType.valueOf(studentType));
    }

    public static StudentEntity from(Student student) {
        return new StudentEntity(student.getId(), student.getStudentName(), student.getEmail(),
            student.getPaymentAmount(), student.getApprovalState(),
            student.getStudentType(), student.getCreatedAt());
    }

    public StudentEntity(Long id, String studentName, String email, int paymentAmount,
        String approvalState, String studentType,
        LocalDateTime createdAt) {
        this(id, studentName, email, paymentAmount, approvalState, studentType, createdAt, null);
    }

    public StudentEntity(Long id, String studentName, String email, int paymentAmount,
        String approvalState, String studentType, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
        this.approvalState = approvalState;
        this.studentType = studentType;
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public String getStudentType() {
        return studentType;
    }
}
