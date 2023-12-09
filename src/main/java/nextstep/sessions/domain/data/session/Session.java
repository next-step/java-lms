package nextstep.sessions.domain.data.session;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.sessions.domain.data.registration.Registration;

public class Session {

    private Long id;
    private final EnrollmentInfo enrollmentInfo;
    private OpenInfo openInfo;

    public Session(EnrollmentInfo enrollmentInfo) {
        this.enrollmentInfo = enrollmentInfo;
    }

    public Session(
        Long id,
        PaidType paidType,
        Long fee,
        int capacity,
        SessionRunningState sessionRunningStateState,
        SessionRecruitingState sessionRecruitingState,
        LocalDateTime startDate,
        LocalDateTime endDate
    ) {
        this(id,
            new EnrollmentInfo(
                new SessionType(paidType, fee, capacity),
                new SessionState(sessionRunningStateState, sessionRecruitingState)),
            new OpenInfo(new Duration(startDate, endDate)));
    }

    public Session(EnrollmentInfo enrollmentInfo, OpenInfo openInfo) {
        this.enrollmentInfo = enrollmentInfo;
        this.openInfo = openInfo;
    }

    public Session(Long id, EnrollmentInfo enrollmentInfo, OpenInfo openInfo) {
        this.id = id;
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

    public Long sessionId() {
        return id;
    }
}
