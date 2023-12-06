package nextstep.courses.domain;

import java.util.List;
import nextstep.users.domain.NsUser;

public class Session {
    private final Long sessionId;
    private final String sessionName;
    private final Period sessionPeriod;
    private final Thumbnail thumbnail;
    private final SessionType sessionType;
    private final SessionStatus sessionStatus;
    private final List<NsUser> students;

    public Session(Long sessionId, String sessionName, Period sessionPeriod, Thumbnail thumbnail,
                   SessionType sessionType, SessionStatus sessionStatus, List<NsUser> students) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionPeriod = sessionPeriod;
        this.thumbnail = thumbnail;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.students = students;
    }

    public boolean isEnrollmentPossible(Integer sessionFee) {
        if (!isRecruiting()) {
            return false;
        }
        if (!isWithinCapacity()) {
            return false;
        }
        if (!checkSessionFeeEquality(sessionFee)) {
            return false;
        }
        return true;
    }
    protected boolean isRecruiting() {
        return sessionStatus == SessionStatus.RECRUITING;
    }

    protected boolean isWithinCapacity() {
        return this.sessionType.isWithinCapacity(this.students.size());
    }

    protected boolean checkSessionFeeEquality(Integer sessionFee) {
        return this.sessionType.checkSessionFeeEquality(sessionFee);
    }

    public void enroll(NsUser student) {
        this.students.add(student);
    }

    public Long getSessionId() {
        return sessionId;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", sessionName='" + sessionName + '\'' +
                ", sessionPeriod=" + sessionPeriod +
                ", thumbnail=" + thumbnail +
                ", sessionType=" + sessionType +
                ", sessionStatus=" + sessionStatus +
                ", students=" + students +
                '}';
    }
}
