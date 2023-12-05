package nextstep.sessions.domain.data;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.*;
import nextstep.users.domain.NsUser;

public class Session {

    private final SessionInfo sessionInfo;
    private Registrations registrations;

    public Session(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public Session(PaidType paidType, Long fee, int capacity, SessionState sessionState, LocalDateTime startDate, LocalDateTime endDate) {
        this.sessionInfo = new SessionInfo(new EnrollmentInfo(new SessionType(paidType, fee, capacity), sessionState), new OpenInfo(new Duration(startDate, endDate)));
    }

    public Session(SessionInfo sessionInfo, List<Registration> registrations) {
        this(sessionInfo, new Registrations(registrations));
    }

    public Session(SessionInfo sessionInfo, Registrations registrations) {
        this.sessionInfo = sessionInfo;
        this.registrations = registrations;
    }

    public Registration registration(NsUser user, Payment payment) {
        sessionInfo.validateEnrollment(registrations.size(), payment);
        registrations.validateDuplicateEnrollment(user);
        return new Registration(this, user, payment);
    }

    public SessionInfo sessionInfo() {
        return sessionInfo;
    }

    public String paidType() {
        return sessionInfo().enrollmentInfo().sessionType().payInfo().paidType().name();
    }

    public long fee() {
        return sessionInfo().enrollmentInfo().sessionType().payInfo().fee();
    }

    public int capacity() {
        return sessionInfo().enrollmentInfo().sessionType().capacity();
    }

    public String sessionState() {
        return sessionInfo().enrollmentInfo().sessionState().name();
    }

    public LocalDateTime startDate() {
        return sessionInfo().openInfo().duration().startDate();
    }

    public LocalDateTime endDate() {
        return sessionInfo().openInfo().duration().endDate();
    }
}
