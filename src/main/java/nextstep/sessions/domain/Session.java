package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.exception.InvalidPaymentException;
import nextstep.payments.exception.PaymentAmountMismatchException;
import nextstep.sessions.exception.DuplicateJoinException;
import nextstep.sessions.exception.InvalidSessionException;
import nextstep.sessions.exception.InvalidSessionJoinException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Session extends BaseEntity {
    private long id;

    private String title;

    private SessionState state;

    private SessionType sessionType;

    private CoverImage coverImage;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Set<NsUser> listener = new HashSet<>();


    public Session(SessionState state) {
        this(state, LocalDateTime.now());
    }

    public Session(SessionState state, LocalDateTime createdAt) {
        this(state, new FreeSession(), createdAt, null, null);
    }

    public Session(SessionState state, SessionType sessionType) {
        this(state, sessionType, null, null, LocalDateTime.now());
    }

    public Session(SessionState state, SessionType sessionType, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt) {
        this(0L, "", state, sessionType, null, startDate, endDate, createdAt, null);
    }

    public Session(long id, String title, SessionState state, SessionType sessionType, CoverImage coverImage, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validatePeriod(startDate, endDate);

        this.id = id;
        this.title = title;
        this.state = state;
        this.sessionType = sessionType;
        this.coverImage = coverImage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void validatePeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new InvalidSessionException("시작일이 종료일보다 이전이어야 합니다");
        }
    }

    public Payment requestJoin(NsUser loginUser, LocalDateTime now) {
        verifySession(now);
        return new Payment(this.id, loginUser.getId(), this.sessionType.getAmount(), now);
    }

    private void verifySession(LocalDateTime now) {
        if (!this.state.isRecruiting()) {
            throw new InvalidSessionJoinException("현재 수강 신청 불가 합니다");
        }

        if (this.sessionType.isFull(this.listener.size())) {
            throw new InvalidSessionJoinException("현재 수강 신청 인원이 모두 가득 찼습니다");
        }

        if (this.startDate == null || this.endDate == null || !isWithinPeriodRange(now)) {
            throw new InvalidSessionJoinException("현재 수강 신청 불가능한 기간 입니다");
        }
    }

    private boolean isWithinPeriodRange(LocalDateTime now) {
        return now.isAfter(this.startDate) && now.isBefore(this.endDate);
    }

    public void join(NsUser loginUser, Payment payment) {
        validateJoin(loginUser, payment);
        addListener(loginUser);
    }

    private void validateJoin(NsUser loginUser, Payment payment) {
        validateNull(loginUser, payment);
        validatePayment(loginUser, payment);
    }

    private void validateNull(NsUser loginUser, Payment payment) {
        if (loginUser == null) {
            throw new NullPointerException("사용자 정보가 존재하지 않습니다");
        }

        if (payment == null) {
            throw new NullPointerException("결제 정보가 존재하지 않습니다");
        }
    }

    private void validatePayment(NsUser loginUser, Payment payment) {
        if (!payment.isPaymentComplete()) {
            throw new InvalidPaymentException("결제 완료 되지 않았습니다");
        }

        if (!payment.equalSessionId(this.id)) {
            throw new InvalidPaymentException("결제 정보와 강의 정보가 일치하지 않습니다");
        }

        if (!payment.equalNsUserId(loginUser)) {
            throw new InvalidPaymentException("결제 정보와 유저 정보가 일치하지 않습니다");
        }

        if (!this.sessionType.equalMoney(payment)) {
            throw new PaymentAmountMismatchException("결제 정보와 강의 금액이 일치하지 않습니다");
        }
    }

    public void addListener(NsUser loginUser) {
        if (listener.contains(loginUser)) {
            throw new DuplicateJoinException("이미 강의 등록된 회원입니다");
        }

        this.listener.add(loginUser);
    }

    public int getCount() {
        return this.listener.size();
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
