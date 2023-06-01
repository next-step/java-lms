package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private final SessionInfo sessionInfo;
    private final SessionEnrollment sessionEnrollment;
    private final SessionTimeLine sessionTimeLine;

    public Session(Long courseId, Long ownerId, String title, String coverImageInfo,
                   SessionType sessionType, SessionStatus sessionStatus,
                   LocalDateTime createdAt, LocalDateTime closedAt, Long maxNumOfStudent) {

            this(new SessionInfo(courseId, ownerId, title, coverImageInfo, sessionType),
                new SessionEnrollment(sessionStatus, maxNumOfStudent),
                new SessionTimeLine(createdAt, closedAt));
    }

    public Session(SessionInfo sessionInfo, SessionEnrollment sessionEnrollment,
                   SessionTimeLine sessionTimeLine){
        this.sessionInfo = sessionInfo;
        this.sessionEnrollment = sessionEnrollment;
        this.sessionTimeLine = sessionTimeLine;
    }

    public void enroll(Student student) {
        sessionEnrollment.enroll(student);
    }

    public Long totalStudentNum() {
        return sessionEnrollment.totalStudentNum();
    }

    public boolean isPositionFull() {
        return sessionEnrollment.isPositionFull();
    }
}
