package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Session {

    private Long id;

    private Long courseId;

    private final SessionImage sessionImage;

    private final SessionPeriod sessionPeriod;

    private final SessionPrice sessionPrice;

    private final SessionState sessionState;

    private final SessionType sessionType;

    private final SessionUsers sessionUsers;

    private final SessionUserCount sessionUserCount;

    public Session(Long id) {
        this(id, null, null, null, null, null, null, null, null);
    }

    public Session(Long id, Long courseId, SessionImage sessionImage, SessionPeriod sessionPeriod, SessionPrice sessionPrice, SessionState sessionState, SessionType sessionType, SessionUserCount sessionUserCount) {
        this(id, courseId, sessionImage, sessionPeriod, sessionPrice, sessionState, sessionType, new SessionUsers(), sessionUserCount);
    }

    public Session(Long id, Long courseId, SessionImage sessionImage, SessionPeriod sessionPeriod, SessionPrice sessionPrice, SessionState sessionState, SessionType sessionType, SessionUsers sessionUsers, SessionUserCount sessionUserCount) {
        this.id = id;
        this.courseId = courseId;
        this.sessionImage = sessionImage;
        this.sessionPeriod = sessionPeriod;
        this.sessionPrice = sessionPrice;
        this.sessionState = sessionState;
        this.sessionType = sessionType;
        this.sessionUsers = sessionUsers;
        this.sessionUserCount = sessionUserCount;
    }

    public void register(NsUser user, Payment payment) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
