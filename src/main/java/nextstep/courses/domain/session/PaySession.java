package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.exception.NotMatchAmountException;
import nextstep.courses.exception.NotRecruitingException;
import nextstep.courses.exception.NotRegisterSession;
import nextstep.courses.exception.SessionEnrollException;

import java.time.LocalDate;

import static nextstep.courses.domain.session.Status.*;

public class PaySession extends Session {

    private Amount amount;
    private int enrollmentCapacity;

    public PaySession(Long id, PayType payType, Status status, CoverImage coverImage, LocalDate startDate, LocalDate endDate, Amount amount, int enrollmentCapacity) {
        super(id, payType, status, coverImage, startDate, endDate);
        this.amount = amount;
        this.enrollmentCapacity = enrollmentCapacity;
    }

    @Override
    public void enroll(SessionStudent sessionStudent) throws SessionEnrollException {
        validateStatus();
        validateCapacity();

        sessionStudents.add(sessionStudent);
    }

    private void validateStatus() throws NotRecruitingException {
        if (isNotRecruiting(status)) {
            throw new NotRecruitingException(String.format("해당 강의의 현재 %s입니다.", status.description()));
        }
    }

    private void validateCapacity() throws NotRegisterSession {
        if (sessionStudents.isExceed(enrollmentCapacity)) {
            throw new NotRegisterSession("현재 수강 가능한 모든 인원수가 채워졌습니다.");
        }
    }

    @Override
    public void isEqual(Long amount) throws NotMatchAmountException {
        this.amount.validatePayAmount(amount);
    }
}
