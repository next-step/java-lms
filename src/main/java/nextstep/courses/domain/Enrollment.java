package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private final SessionPrice sessionPrice;
    private final SessionState sessionState;
    private final SessionType sessionType;
    private final SessionUserCount sessionUserCount;
    private final SessionUsers sessionUsers;

    public Enrollment(SessionPrice sessionPrice, SessionState sessionState, SessionType sessionType, SessionUserCount sessionUserCount) {
        this(sessionPrice, sessionState, sessionType, sessionUserCount, new SessionUsers());
    }

    public Enrollment(SessionPrice sessionPrice, SessionState sessionState, SessionType sessionType, SessionUserCount sessionUserCount, SessionUsers sessionUsers) {
        this.sessionPrice = sessionPrice;
        this.sessionState = sessionState;
        this.sessionType = sessionType;
        this.sessionUserCount = sessionUserCount;
        this.sessionUsers = sessionUsers;
    }

    public void enroll(NsUser user, Payment payment) {
        validateState();
        validateType();
        validatePriceEqualPayment(payment);
        sessionUsers.addUser(user);
        sessionUserCount.plusUserCount();
    }

    private void validatePriceEqualPayment(Payment payment) {
        if (isPaid()) {
            sessionPrice.isSameBy(payment);
        }
    }

    private void validateState() {
        if (!isOpen()) {
            throw new SessionException("현재 강의가 모집중이지 않아 수강 신청을 할 수가 없습니다.");
        }
    }

    private boolean isOpen() {
        return sessionState.isOpen();
    }

    private void validateType() {
        if (isPaid()) {
            sessionUserCount.validateMaxUserCount();
        }
    }

    private boolean isPaid() {
        return sessionType == SessionType.PAID;
    }

}
