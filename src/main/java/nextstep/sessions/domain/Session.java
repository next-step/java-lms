package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Session extends BaseEntity {
    private long id;

    private String title;

    private SessionState state;

    private int count;

    private SessionType sessionType;

    private Set<NsUser> listener = new HashSet<>();

    public Session(String title) {
        this(title, SessionState.PREPARING);
    }

    public Session(String title, SessionState state) {
        this(title, state, LocalDateTime.now());
    }

    public Session(String title, SessionState state, LocalDateTime createdAt) {
        this(title, state, createdAt, new FreeSession());
    }

    public Session(String title, SessionState state, LocalDateTime createdAt, SessionType sessionType) {
        this(0L, title, state, createdAt, null, 0, sessionType);
    }

    public Session(String title, SessionState state, int count, SessionType sessionType) {
        this(0L, title, state, LocalDateTime.now(), null, count, sessionType);
    }

    public Session(long id, String title, SessionState state, LocalDateTime createdAt, LocalDateTime updatedAt, int count, SessionType sessionType) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.count = count;
        this.sessionType = sessionType;
    }

    public Payment requestJoin(NsUser loginUser, LocalDateTime now) {
        verifySession();
        return new Payment(this.id, loginUser.getId(), this.sessionType.getAmount(), now);
    }

    private void verifySession() {
        if (!this.state.isRecruiting()) {
            throw new IllegalArgumentException("현재 수강 신청 불가 합니다");
        }

        if (this.sessionType.isFull(this.count)) {
            throw new IllegalArgumentException("현재 수강 신청 인원이 모두 가득 찼습니다");
        }
    }

    public void join(NsUser loginUser, Payment payment) {
        verifySession();
        validateJoin(loginUser, payment);
        addListener(loginUser);
    }

    private void validateJoin(NsUser loginUser, Payment payment) {
        validateNull(loginUser, payment);
        validatePayment(loginUser, payment);
    }

    private void validateNull(NsUser loginUser, Payment payment) {
        if (loginUser == null) {
            throw new IllegalArgumentException();
        }

        if (payment == null) {
            throw new IllegalArgumentException();
        }
    }

    private void validatePayment(NsUser loginUser, Payment payment) {
        if (!payment.isPaymentComplete()) {
            throw new IllegalArgumentException();
        }

        if (!payment.equalSessionId(this.id)) {
            throw new IllegalArgumentException();
        }

        if (!payment.equalNsUserId(loginUser)) {
            throw new IllegalArgumentException();
        }

        if (!this.sessionType.equalMoney(payment)) {
            throw new IllegalArgumentException();
        }
    }

    public void addListener(NsUser loginUser) {
        if (listener.contains(loginUser)) {
            throw new IllegalArgumentException();
        }

        this.listener.add(loginUser);
        this.count += 1;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Session session = (Session) other;
        return id == session.id && Objects.equals(title, session.title) && state == session.state && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, state, createdAt, updatedAt);
    }
}
