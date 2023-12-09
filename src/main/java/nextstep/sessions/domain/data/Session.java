package nextstep.sessions.domain.data;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.sessions.domain.data.type.*;
import nextstep.sessions.domain.data.vo.*;

public class Session {

    private final EnrollmentInfo enrollmentInfo;
    private OpenInfo openInfo;

    public Session(EnrollmentInfo enrollmentInfo) {
        this.enrollmentInfo = enrollmentInfo;
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
        this(new EnrollmentInfo(
                new SessionType(paidType, fee, capacity),
                new NewSessionState(sessionRunningStateState, sessionRecruitingState)),
            new OpenInfo(new Duration(startDate, endDate)));
    }

    public Session(EnrollmentInfo enrollmentInfo, OpenInfo openInfo) {
        this.enrollmentInfo = enrollmentInfo;
        this.openInfo = openInfo;
    }

    public Enrollment enrollment(List<Registration> registrations) {
        return new Enrollment(enrollmentInfo, registrations);
    }

    public String paidType() {
        return enrollmentInfo.paidType();
    }

    public long fee() {
        return enrollmentInfo.fee();
    }

    public int capacity() {
        return enrollmentInfo.capacity();
    }

    public String sessionRunningState() {
        return enrollmentInfo.sessionRunningState();
    }

    public String sessionRecruitingState() {
        return enrollmentInfo.sessionRecruitingState();
    }

    public LocalDateTime startDate() {
        return openInfo.startDate();
    }

    public LocalDateTime endDate() {
        return openInfo.endDate();
    }
}
