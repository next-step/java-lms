package nextstep.courses.domain;


import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private Long id;
    private final Long courseId;
    private final LocalDateTime createdAt;
    private final String image;
    private final LectureType lectureType;
    private final SessionDate sessionDate;
    private final SessionInfo sessionInfo;

    public Session(Long id, Long courseId, String image, LectureType lectureType, SessionDate sessionDate, SessionInfo sessionInfo) {
        this(id,courseId,image,lectureType,sessionDate,sessionInfo,LocalDateTime.now());
    }

    public Session(Long id, Long courseId, String image, LectureType lectureType, SessionDate sessionDate, SessionInfo sessionInfo, LocalDateTime createdAt) {
        this.id = id;
        this.courseId = courseId;
        this.image = image;
        this.lectureType = lectureType;
        this.sessionDate = sessionDate;
        this.sessionInfo = sessionInfo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void register(NsUser user) {
        sessionInfo.register(new Student(id, user.getId()));
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public LocalDate getStartDate() {
        return sessionDate.getStartDate();
    }

    public LocalDate getEndDate() {
        return sessionDate.getEndDate();
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getCourseId() {
        return courseId;
    }

    public int getMaxUser() {
        return sessionInfo.getMaxUser();
    }

    public LectureStatus getLectureStatus() {
        return sessionInfo.getLectureStatus();
    }

    public void setId(Long id) {
        this.id = id;
    }

}