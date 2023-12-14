package nextstep.session.domain;

import nextstep.common.BaseTimeEntity;
import nextstep.image.domain.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private Long id;

    private Users members;

    private Long amount = 0L;

    private SessionType sessionType;

    private SessionStatus status;

    private Image coverImage;

    private BaseTimeEntity baseTime;

    public Session(Long id, Users members, Long amount, SessionType sessionType, SessionStatus status, Image coverImage, BaseTimeEntity baseTime) {
        this.id = id;
        this.members = members;
        this.amount = amount;
        this.sessionType = sessionType;
        this.status = status;
        this.coverImage = coverImage;
        this.baseTime = baseTime;
    }

    public static Session create(
            Users members,
            Long amount,
            SessionType sessionType,
            Image coverImage,
            LocalDateTime startAt,
            LocalDateTime endAt
    ) {
        requiredNotBeforeCurrent(startAt, "시작일은 현재보다 이전일 수 없습니다.");
        requiredNotBeforeCurrent(endAt, "종료일은 현재보다 이전일 수 없습니다.");
        return new Session(
                null,
                members,
                amount,
                sessionType,
                SessionStatus.PREPARING,
                coverImage,
                new BaseTimeEntity(startAt, endAt)
        );
    }

    public Long getId() {
        return id;
    }

    public Users getMembers() {
        return members;
    }

    public Long getAmount() {
        return amount;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public BaseTimeEntity getBaseTime() {
        return baseTime;
    }

    private static void requiredNotBeforeCurrent(LocalDateTime localDateTime, String exceptionMessage) {
        if (localDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private void validatePaid(Payment payment) {
        if (Objects.nonNull(payment) && this.amount.equals(payment.amount())) {
            throw new IllegalArgumentException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }
    }

    public void register(NsUser user, Payment payment) {
        if (!status.isRegistrable()) {
            throw new IllegalStateException("수강 신청 불가능한 상태입니다.");
        }

        if (sessionType == SessionType.PAID) {
            validatePaid(payment);
        }

        members.register(user, sessionType);
    }

    public int memberSize() {
        return members.size();
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", members=" + members +
                ", amount=" + amount +
                ", sessionType=" + sessionType +
                ", status=" + status +
                ", coverImage=" + coverImage +
                ", baseTime=" + baseTime +
                '}';
    }
}
