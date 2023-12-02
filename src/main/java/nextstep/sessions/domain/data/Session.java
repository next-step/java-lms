package nextstep.sessions.domain.data;

import java.time.LocalDateTime;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.*;

public class Session {

    private final SessionInfo sessionInfo;
    private Registrations registrations;

    public Session(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public Session(SessionType sessionType, CoverImage coverImage, LocalDateTime startDate, LocalDateTime endDate) {
        this.sessionInfo = new SessionInfo(
            new EnrollmentInfo(sessionType, SessionState.PREPARING),
            new OpenInfo(coverImage, new Duration(startDate, endDate))
        );
    }

    public Session(SessionInfo sessionInfo, Registrations registrations) {
        this.sessionInfo = sessionInfo;
        this.registrations = registrations;
    }

    public Registration registration(Payment payment) {
        sessionInfo.validateEnrollment(registrations.size(), payment);
        return new Registration(this, payment);
    }

}
