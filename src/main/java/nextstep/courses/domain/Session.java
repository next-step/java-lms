package nextstep.courses.domain;

import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.courses.domain.registration.SessionRecruitmentStatus;
import nextstep.courses.domain.registration.SessionStatus;
import nextstep.courses.domain.registration.Student;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private Long id;
    private final SessionInfo sessionInfo;
    private final SessionRegistration sessionRegistration;
    private final SessionPeriod sessionPeriod;

    public Session(Long courseId, Long ownerId, String title, String coverImageInfo,
                   SessionType sessionType, SessionStatus sessionStatus,
                   LocalDateTime createdAt, LocalDateTime closedAt, Long maxNumOfStudent) {

        this(new SessionInfo(courseId, ownerId, title, coverImageInfo, sessionType),
                new SessionRegistration(sessionStatus, maxNumOfStudent),
                new SessionPeriod(createdAt, closedAt));
    }

    public Session(SessionInfo sessionInfo, SessionRegistration sessionRegistration,
                   SessionPeriod sessionPeriod){
        this.sessionInfo = sessionInfo;
        this.sessionRegistration = sessionRegistration;
        this.sessionPeriod = sessionPeriod;
    }

    public Student register(NsUser nsUser) {
        Student student = new Student(nsUser.getId(), this.id);
        sessionRegistration.enroll(student);
        return student;
    }

    public Long totalStudentNum() {
        return sessionRegistration.totalStudentNum();
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
        return sessionRegistration.getStatus();
    }

    public Long getTotalStudentNum() {
        return sessionRegistration.totalStudentNum();
    }

    public LocalDateTime getCreateAt() {
        return sessionPeriod.getCreateAt();
    }

    public LocalDateTime getCloseAt() {
        return sessionPeriod.getCloseAt();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return sessionRegistration.getSessionRecruitmentStatus();
    }

}
