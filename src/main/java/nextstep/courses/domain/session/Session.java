package nextstep.courses.domain.session;

import nextstep.courses.domain.capacity.Capacity;
import nextstep.courses.domain.enroll.Enrollment;
import nextstep.courses.domain.enroll.Enrollments;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.money.Money;
import nextstep.courses.domain.policy.SessionPolicy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private final SessionInfo sessionInfo;
    private final CoverImage coverImage;
    private final Enrollments enrollments;
    private final Long id;

    public Session(Long id, SessionStatus sessionStatus, SessionPolicy sessionPolicy, LocalDateTime startDateTime, LocalDateTime endDateTime, int capacity, CoverImage coverImage, Enrollments enrollments) {
        this(id, new SessionInfo(sessionStatus, sessionPolicy, new SessionPeriod(startDateTime, endDateTime), new Capacity(capacity)), coverImage, enrollments);
    }

    public Session(Long id, SessionInfo sessionInfo, CoverImage coverImage, Enrollments enrollments) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.coverImage = coverImage;
        this.enrollments = enrollments;
    }

    public Payment enroll(String paymentId, NsUser nsUser, Long amount, LocalDateTime enrolledDateTime) {
        sessionInfo.validateEnrollment(new Money(amount), enrolledDateTime);
        enrollments.addEnrollment(new Enrollment(nsUser.getId(), id));

        return new Payment(paymentId, id, nsUser.getId(), amount);
    }
}
