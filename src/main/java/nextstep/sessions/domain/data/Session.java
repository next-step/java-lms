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

    public String paidType() {
        return sessionInfo.paidType();
    }

    public long fee() {
        return sessionInfo.fee();
    }

    public int capacity() {
        return sessionInfo.capacity();
    }

    public String sessionRunningState() {
        return sessionInfo.sessionRunningState();
    }

    public String sessionRecruitingState() {
        return sessionInfo.sessionRecruitingState();
    }

    public LocalDateTime startDate() {
        return sessionInfo.startDate();
    }

    public LocalDateTime endDate() {
        return sessionInfo.endDate();
    }

}
