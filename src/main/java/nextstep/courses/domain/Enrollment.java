package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private final SessionPrice sessionPrice;
    private final SessionStatus sessionStatus;
    private final SessionRecruitment sessionRecruitment;
    private final SessionType sessionType;
    private final SessionUserCount sessionUserCount;
    private final SessionUsers sessionUsers;

    public Enrollment(SessionPrice sessionPrice, SessionStatus sessionStatus, SessionRecruitment sessionRecruitment, SessionType sessionType, SessionUserCount sessionUserCount) {
        this(sessionPrice, sessionStatus, sessionRecruitment, sessionType, sessionUserCount, new SessionUsers());
    }

    public Enrollment(SessionPrice sessionPrice, SessionStatus sessionStatus, SessionRecruitment sessionRecruitment, SessionType sessionType, SessionUserCount sessionUserCount, SessionUsers sessionUsers) {
        this.sessionPrice = sessionPrice;
        this.sessionStatus = sessionStatus;
        this.sessionRecruitment = sessionRecruitment;
        this.sessionType = sessionType;
        this.sessionUserCount = sessionUserCount;
        this.sessionUsers = sessionUsers;
    }

    public SessionUser enroll(long sessionId, SelectionStatus selectionStatus, NsUser user, Payment payment) {
        validateRecruitment();
        validateStatus();
        validateType();
        validatePriceEqualPayment(payment);
        sessionUserCount.plusUserCount();
        return sessionUsers.addUser(sessionId, selectionStatus, user);
    }

    private void validatePriceEqualPayment(Payment payment) {
        if (isPaid()) {
            sessionPrice.isSameBy(payment);
        }
    }

    private void validateRecruitment(){
        if(!sessionRecruitment.isRecruitment()){
            throw new SessionException("현재 강의가 모집중이지 않아 수강 신청을 할 수가 없습니다.");
        }
    }

    private void validateStatus(){
        if(sessionStatus.isComplete()){
            throw new SessionException("현재 강의가 종료되어 수강 신청을 할 수가 없습니다.");
        }
    }

    private void validateType() {
        if (isPaid()) {
            sessionUserCount.validateMaxUserCount();
        }
    }

    private boolean isPaid() {
        return sessionType == SessionType.PAID;
    }

    public SessionUser approve(NsUser user) {
        return sessionUsers.approve(user);
    }

    public SessionUser cancel(NsUser user) {
        return sessionUsers.cancel(user);
    }
}
