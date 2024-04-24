package nextstep.courses.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.student.Student;

public class StudentEntity extends BaseEntity {

    private Long id;

    private String studentName;

    private String email;

    private int paymentAmount;

    public static StudentEntity from(Student student){
        return new StudentEntity(null, student.getStudentName(), student.getEmail(),
            student.getPaymentAmount(), student.getCreatedAt());
    }

    public StudentEntity(Long id, String studentName, String email, int paymentAmount, LocalDateTime createdAt) {
        this(id, studentName, email, paymentAmount, createdAt, null);
    }


    public StudentEntity(Long id, String studentName, String email, int paymentAmount) {
        this(id, studentName, email, paymentAmount, null, null);
    }

    public StudentEntity(Long id,
        String studentName,
        String email, int paymentAmount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.studentName = studentName;
        this.email = email;
        this.paymentAmount = paymentAmount;
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
}
