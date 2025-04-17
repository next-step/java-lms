package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Enrollment {
    private Long id;
    private Session session;
    private Student student;
    private Payment payment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(Session session, Student student, Payment payment) {
        this.session = session;
        this.student = student;
        this.payment = payment;
    }

    public void enroll() {
        session.enroll(this);
        student.addEnrollment(this);
    }

    public Payment getPayment() {
        return payment;
    }
}
