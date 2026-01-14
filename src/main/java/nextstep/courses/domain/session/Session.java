package nextstep.courses.domain.session;

import nextstep.courses.domain.capacity.Capacity;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.money.Money;
import nextstep.courses.domain.policy.SessionPolicy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private final SessionInfo sessionInfo;
    private final CoverImage coverImage;

    public Session(Long id, SessionStatus sessionStatus, SessionPolicy sessionPolicy, LocalDateTime startDateTime, LocalDateTime endDateTime, int capacity, CoverImage coverImage) {
        this(id, new SessionInfo(sessionStatus, sessionPolicy, new SessionPeriod(startDateTime, endDateTime), new Capacity(capacity)), coverImage);
    }

    public Session(Long id, SessionInfo sessionInfo, CoverImage coverImage) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.coverImage = coverImage;
    }

    public Payment enroll(String paymentId, NsUser nsUser, Long amount, LocalDateTime enrolledDateTime) {
        sessionInfo.validateEnrollment(new Money(amount), enrolledDateTime);
        return new Payment(paymentId, id, nsUser.getId(), amount);
    }
}
