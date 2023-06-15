package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.SessionEnrollment;
import nextstep.courses.domain.enrollment.SessionRecruitmentStatus;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.Student;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private Long id;
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

    public Student register(NsUser nsUser) {
        Student student = new Student(nsUser.getId(), this.id);
        sessionEnrollment.enroll(student);
        return student;
    }

    public Long totalStudentNum() {
        return sessionEnrollment.totalStudentNum();
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return sessionInfo.getCourseId();
    }

    public Long getOwnerId() {
        return sessionInfo.getOwnerId();
    }

    public String getTitle() {
        return sessionInfo.getTitle();
    }

    public String getCoverImageInfo() {
        return sessionInfo.getCoverImageInfo();
    }

    public SessionType getSessionType() {
        return sessionInfo.getSessionType();
    }

    public SessionStatus getStatus() {
        return sessionEnrollment.getStatus();
    }

    public Long getTotalStudentNum() {
        return sessionEnrollment.totalStudentNum();
    }

    public LocalDateTime getCreateAt() {
        return sessionTimeLine.getCreateAt();
    }

    public LocalDateTime getCloseAt() {
        return sessionTimeLine.getCloseAt();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return sessionEnrollment.getSessionRecruitmentStatus();
    }

}
