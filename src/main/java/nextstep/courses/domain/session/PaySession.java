package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.payments.domain.Payment;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static nextstep.courses.domain.session.Status.*;

public class PaySession extends Session {

    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    private Long amount;
    private int studentsCapacity;

    public PaySession(Long id, PayType payType, Status status, CoverImage coverImage, LocalDate startDate, LocalDate endDate, Long amount, int studentsCapacity) {
        super(id, payType, status, coverImage, startDate, endDate);
        this.amount = amount;
        this.studentsCapacity = studentsCapacity;
    }

    @Override
    public void enroll(SessionStudent sessionStudent, Payment payment) {
        validateStatus();
        validatePayAmount(payment);
        validateCapacity();

        sessionStudents.add(sessionStudent);
    }

    private void validateStatus() {
        if (isNotRecruiting(status)) {
            throw new IllegalArgumentException(String.format("해당 강의는 현재 %s입니다.", status.description()));
        }
    }

    private void validatePayAmount(Payment payment) {
        if (payment.isNotSameAmount(amount)) {
            throw new IllegalArgumentException(String.format("결제 금액이 강의 금액과 일치하지 않습니다. 강의 금액 :: %s원", formatter.format(amount)));
        }
    }

    private void validateCapacity() {
        if (isExceed()) {
            throw new IllegalArgumentException("현재 수강 가능한 모든 인원수가 채워졌습니다.");
        }
    }

    private boolean isExceed() {
        return sessionStudents.size() >= studentsCapacity;
    }
}
