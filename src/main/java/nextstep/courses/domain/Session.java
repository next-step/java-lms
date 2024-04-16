package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.PeriodException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Long id;
    private final SessionInfo sessionInfo;
    private final ChargeInfo chargeInfo;
    private SessionStatus sessionStatus;
    private final List<NsUser> students = new ArrayList<>();

    public Session(Long id, String title, LocalDate startAt, LocalDate endAt, Image coverImage,
                   ChargeType chargeType, long price, int studentCapacity, SessionStatus sessionStatus) throws PeriodException {
        this.id = id;
        this.sessionInfo = new SessionInfo(title, coverImage, new Period(startAt, endAt), studentCapacity);
        this.chargeInfo = new ChargeInfo(chargeType, price);
        this.sessionStatus = sessionStatus;
    }

    public void enroll(NsUser student, int payment) throws CannotEnrollException {
        if (sessionInfo.isSessionFull(enrolledStudents())) {
            throw new CannotEnrollException("최대수강인원을 초과할 수 없습니다.");
        }

        if (!chargeInfo.isPaymentValid(payment)) {
            throw new CannotEnrollException("결제한 금액이 수강료와 다릅니다.");
        }

        if (!sessionStatus.isEnrolling()) {
            throw new CannotEnrollException("모집 중인 강의가 아닙니다.");
        }

        students.add(student);
    }

    public int enrolledStudents() {
        return students.size();
    }

    public void closeSession() {
        this.sessionStatus = SessionStatus.CLOSED;
    }

}
