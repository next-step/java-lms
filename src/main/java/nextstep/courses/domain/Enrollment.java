package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class Enrollment {
    private Session session;
    private Student student;
    private Payment payment;

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
