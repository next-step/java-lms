package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class SessionEnroll {
    private Session session;
    private Student student;
    private Payment payment;

    public SessionEnroll(Session session, Student student, Payment payment) {
        if (session.isValidNumberOfStudents()) {
            throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }
        if (session.isValidPayAmount(payment)) {
            throw new IllegalArgumentException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }
        if (session.isValidStatus()) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집 중일 때만 가능합니다.");
        }
        this.session = session;
        this.student = student;
        this.payment = payment;

        session.increaseAppNumber();
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
