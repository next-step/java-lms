package nextstep.sessions.domain.data;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.*;
import nextstep.sessions.domain.data.vo.*;
import nextstep.users.domain.NsUser;

public class Session {

    private final SessionInfo sessionInfo;
    private Registrations registrations;

    public Session(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public Session(
        PaidType paidType,
        Long fee,
        int capacity,
        SessionRunningState sessionRunningStateState,
        SessionRecruitingState sessionRecruitingState,
        LocalDateTime startDate,
        LocalDateTime endDate
    ) {
        this.sessionInfo = new SessionInfo(
            new EnrollmentInfo(new SessionType(paidType, fee, capacity), new NewSessionState(sessionRunningStateState, sessionRecruitingState)),
            new OpenInfo(new Duration(startDate, endDate)));
    }

    public Session(SessionInfo sessionInfo, List<Registration> registrations) {
        this(sessionInfo, new Registrations(registrations));
    }

    public Session(SessionInfo sessionInfo, Registrations registrations) {
        this.sessionInfo = sessionInfo;
        this.registrations = registrations;
    }

    public Session with(List<Registration> registrations) {
        return new Session(sessionInfo, registrations);
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

    public String sessionRunningState() {
        return sessionInfo().enrollmentInfo().newSessionState().sessionRunningState().name();
    }

    public String sessionRecruitingState() {
        return sessionInfo().enrollmentInfo().newSessionState().sessionRecruitingState().name();
    }

    public LocalDateTime startDate() {
        return sessionInfo().openInfo().duration().startDate();
    }

    public LocalDateTime endDate() {
        return sessionInfo().openInfo().duration().endDate();
    }
}
