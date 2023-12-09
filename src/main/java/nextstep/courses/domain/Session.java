package nextstep.courses.domain;

import java.util.List;
import nextstep.courses.exception.SessionException;
import nextstep.courses.exception.SessionException.SessionFeeNotEqualException;
import nextstep.courses.exception.SessionException.SessionFullException;
import nextstep.courses.exception.SessionException.SessionNotOpenException;
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
            throw new SessionNotOpenException("강의가 모집중인 상태가 아닙니다.");
        }
        if (!isWithinCapacity(students)) {
            throw new SessionFullException("강의의 수용인원이 다 찼습니다.");
        }
        if (!checkSessionFeeEquality(sessionFee)) {
            throw new SessionFeeNotEqualException("접수하신 수강료가 강의 수강료와 일치하지 않습니다.");
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

    public Long getSessionId() {
        return sessionId;
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
