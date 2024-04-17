package nextstep.courses.domain.session;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;
import nextstep.courses.domain.SessionImage;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Session {
    private final Long sessionId;
    private final Long courseId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final SessionImage sessionImage;
    private final SessionStatus sessionStatus;
    private final SessionType sessionType;
    private final List<NsUser> students = new ArrayList<>();

    protected Session(
            Long sessionId,
            Long courseId,
            SessionImage sessionImage,
            LocalDateTime startTime,
            LocalDateTime endTime,
            SessionStatus sessionStatus,
            SessionType sessionType
    ) {
        this.sessionId = sessionId;
        this.courseId = courseId;
        this.sessionImage = sessionImage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }

    public abstract void enroll(NsUser nsUser);

    protected void validateSession(NsUser nsUser) {
        validateEnrollSessionStatus();
        validateEnrolledStudent(nsUser);
    }

    private void validateEnrollSessionStatus() {
        if (!sessionStatus.equals(SessionStatus.RECRUITING)) {
            throw new IllegalArgumentException("수강 모집중인 세션이 아닙니다.");
        }
    }

    private void validateEnrolledStudent(NsUser nsUser) {
        if (isEnrolled(nsUser)) {
            throw new IllegalArgumentException("이미 수강 중인 학생입니다.");
        }
    }

    protected int getEnrolledStudentCount() {
        return students.size();
    }

    protected void enrollStudent(NsUser nsUser) {
        students.add(nsUser);
    }

    protected boolean isEnrolled(NsUser nsUser) {
        return students.contains(nsUser);
    }
}
