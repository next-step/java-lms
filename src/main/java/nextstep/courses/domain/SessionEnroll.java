package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class SessionEnroll {
    private final Session session;
    private final Student student;
    private final Payment payment;

    public SessionEnroll(Session session, Student student, Payment payment) {
        this.session = session;
        this.student = student;
        this.payment = payment;
    }

    public Session getSession() {
        return session;
    }

    public Student getStudent() {
        return student;
    }

    public Payment getPayment() {
        return payment;
    }
}
