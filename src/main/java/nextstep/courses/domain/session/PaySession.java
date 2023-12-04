package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.exception.NotMatchAmountException;
import nextstep.courses.exception.NotRegisterSession;

import java.time.LocalDate;

public class PaySession extends Session {

    private Amount amount;
    private int limit;

    public PaySession(Long id, PayType payType, Status status, CoverImage coverImage, LocalDate startDate, LocalDate endDate, Amount amount, int limit) {
        super(id, payType, status, coverImage, startDate, endDate);
        this.amount = amount;
        this.limit = limit;
    }

    @Override
    public void enroll(SessionStudent sessionStudent) throws NotRegisterSession {
        sessionStudents.validateLimit(limit);
        sessionStudents.add(sessionStudent);
    }

    @Override
    public void isEqual(Long amount) throws NotMatchAmountException {
        this.amount.validate(amount);
    }
}
