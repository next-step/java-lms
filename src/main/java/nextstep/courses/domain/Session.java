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

    public Session(Long sessionId, String sessionName, Period sessionPeriod, Thumbnail thumbnail,
                   SessionType sessionType, SessionStatus sessionStatus) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionPeriod = sessionPeriod;
        this.thumbnail = thumbnail;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
    }

    public boolean isEnrollmentPossible(SessionStudents students, Integer sessionFee) {
        if (!isRecruiting()) {
            return false;
        }
        if (!isWithinCapacity(students)) {
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

    protected boolean isWithinCapacity(SessionStudents students) {
        return students.isWithinCapacity(this.sessionType);
    }

    protected boolean checkSessionFeeEquality(Integer sessionFee) {
        return this.sessionType.checkSessionFeeEquality(sessionFee);
    }

    public Student enroll(SessionStudents students, NsUser user) {
        Student student = new Student(this.sessionId, user.getId());
        students.enroll(student);
        return student;
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
                '}';
    }
}
