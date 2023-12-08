package nextstep.sessions.domain.data;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.sessions.domain.data.type.*;
import nextstep.sessions.domain.data.vo.*;

public class Session {

    private final SessionInfo sessionInfo;

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

    public Enrollment enrollment(List<Registration> registrations) {
        return new Enrollment(sessionInfo, registrations);
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
