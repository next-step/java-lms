package nextstep.session.domain;

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

    private StartAt startAt;

    private EndAt endAt;

    public Session(Long id, Users members, Long amount, SessionType sessionType, SessionStatus status, Image coverImage, StartAt startAt, EndAt endAt) {
        this.id = id;
        this.members = members;
        this.amount = amount;
        this.sessionType = sessionType;
        this.status = status;
        this.coverImage = coverImage;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Session create(
            Users members,
            Long amount,
            SessionType sessionType,
            Image coverImage,
            LocalDateTime startAt,
            LocalDateTime endAt
    ) {
        return new Session(
                null,
                members,
                amount,
                sessionType,
                SessionStatus.PREPARING,
                coverImage,
                new StartAt(startAt),
                new EndAt(endAt)
        );
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

    public Long id() {
        return id;
    }

    public int memberSize() {
        return members.size();
    }
}
