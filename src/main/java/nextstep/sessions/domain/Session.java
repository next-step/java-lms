package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    public static final int ZERO = 0;

    private final Long id;
    private final Long courseId;
    private final SessionPeriod sessionPeriod;
    private final SessionImage sessionImage;
    private final LocalDateTime createdAt;
    private SessionStatus sessionStatus;
    private final SessionType sessionType;
    private final NsUsers nsUsers;
    private UserCount maxUserCount;
    private Price price;


    private Session(Long id, Long courseId, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = sessionPeriod;
        this.sessionImage = sessionImage;
        this.nsUsers = new NsUsers();
        this.sessionStatus = sessionStatus;
        this.sessionType = SessionType.FREE;
        this.createdAt = LocalDateTime.now();
    }

    private Session(Long id, Long courseId, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus, int maxUserCount, int price) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = sessionPeriod;
        this.sessionImage = sessionImage;
        this.createdAt = LocalDateTime.now();
        this.sessionStatus = sessionStatus;
        this.sessionType = SessionType.PAY;
        this.nsUsers = new NsUsers();
        this.maxUserCount = new UserCount(maxUserCount);
        this.price = new Price(price);
    }

    public static Session createFreeSession(Long id, Long courseId, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus) {
        return new Session(id, courseId, sessionPeriod, sessionImage, sessionStatus);
    }

    public static Session createPaySession(Long id, Long courseId, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus, int maxUserCount, int price) {
        validatePositive(maxUserCount);
        validatePositive(price);
        return new Session(id, courseId, sessionPeriod, sessionImage, sessionStatus, maxUserCount, price);
    }

    public void changeSessionStatusIsClose() {
        this.sessionStatus = SessionStatus.CLOSE;
    }

    public void changeSessionStatusIsPreparing() {
        this.sessionStatus = SessionStatus.PREPARING;
    }

    public void changeSessionStatusIsRecruiting() {
        this.sessionStatus = SessionStatus.RECRUITING;
    }

    public void signUp(NsUser nsUser, Payment payment) {
        checkSessionStatus();
        if (sessionType.equals(SessionType.PAY)) {
            checkSessionUserCount();
            payment.checkPayment(this.id, nsUser.getId(), this.price);
        }
        this.nsUsers.add(nsUser);
    }

    private void checkSessionStatus() {
        if (!SessionStatus.RECRUITING.equals(this.sessionStatus)) {
            throw new IllegalArgumentException("현재 강의는 수강 모집 중이 아닙니다.");
        }
    }

    private static void validatePositive(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException("0보다 작은 수가 올 수 없습니다.");
        }
    }

    private void checkSessionUserCount() {
        if (!maxUserCount.biggerThan(this.nsUsers.size())) {
            changeSessionStatusIsClose();
            throw new IllegalArgumentException("유료 강의의 최대 수강인원을 초과할 수 없습니다.");
        }
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
