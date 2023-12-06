package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.exception.NotMatchAmountException;
import nextstep.courses.exception.NotRecruitingException;
import nextstep.courses.exception.NotRegisterSession;
import nextstep.courses.exception.SessionEnrollException;
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
    public void enroll(SessionStudent sessionStudent, Payment payment) throws SessionEnrollException {
        validateStatus();
        validatePayAmount(payment);
        validateCapacity();

        sessionStudents.add(sessionStudent);
    }

    private void validateStatus() throws NotRecruitingException {
        if (isNotRecruiting(status)) {
            throw new NotRecruitingException(String.format("해당 강의의 현재 %s입니다.", status.description()));
        }
    }

    private void validatePayAmount(Payment payment) throws NotMatchAmountException {
        if (payment.isNotSameAmount(amount)) {
            throw new NotMatchAmountException(String.format("결제 금액이 강의 금액과 일치하지 않습니다. 강의 금액 :: %s원", formatter.format(amount)));
        }
    }

    private void validateCapacity() throws NotRegisterSession {
        if (sessionStudents.isExceed(studentsCapacity)) {
            throw new NotRegisterSession("현재 수강 가능한 모든 인원수가 채워졌습니다.");
        }
    }
}
