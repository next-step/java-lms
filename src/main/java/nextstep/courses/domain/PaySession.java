package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaySession extends Session {

    private final int maxCountOfStudents;

    private final Long price;

    public PaySession(Image image, Enrollment enrollment, int maxCountOfStudents, Long price) {
        super(image, SessionType.PAY, enrollment);
        this.maxCountOfStudents = maxCountOfStudents;
        this.price = price;
    }

    @Override
    public void enroll(NsUser student, Payment payment) {
        if (this.maxCountOfStudents < this.getEnrollment().getStudents().size() + 1) {
            throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");
        }
        if (!payment.isMatch(this.price)) {
            throw new IllegalArgumentException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }
        this.getEnrollment().enroll(student);
    }

    public int getMaxCountOfStudents() {
        return this.maxCountOfStudents;
    }
}
