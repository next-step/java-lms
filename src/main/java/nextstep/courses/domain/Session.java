package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private static final String NOT_RECRUITING_SESSION = "모집중인 강이가 아닙니다.";

    private Long id;
    private String title;
    private LocalDateTime createAt;
    private SessionDuration sessionDuration;
    private ImgFile imageFile;
    private boolean free = false;
    private SessionType status = SessionType.READY;
    private Students students;
    private Long courseId;

    public Session(Long id, String title, String imageFile, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.title = title;
        this.students = new Students();
        this.imageFile = new ImgFile(imageFile);
        sessionDuration = new SessionDuration(startedAt, endedAt);
    }

    public Session(Long id, String title, boolean free, LocalDateTime createAt, LocalDateTime startedAt, LocalDateTime endedAt, String imageFile, SessionType sessionType) {
        this.id = id;
        this.title = title;
        this.createAt = createAt;
        this.sessionDuration = new SessionDuration(startedAt, endedAt);
        this.imageFile =  new ImgFile(imageFile);
        this.free = free;
        this.status = sessionType;
        this.students = new Students();
    }

    public Session(Long id, String title, String imageFile, LocalDateTime startedAt, LocalDateTime endedAt, Long courseId) {
        this.id = id;
        this.title = title;
        this.students = new Students();
        this.imageFile = new ImgFile(imageFile);
        sessionDuration = new SessionDuration(startedAt, endedAt);
        this.courseId = courseId;
    }

    public void putStudent(NsUser nsUser) {
        checkValidation();
        students.putEntity(nsUser);
    }

    public void changeStudents(Students students) {
        this.students = students;
    }

    public void removeStudent(NsUser nsUser) {
        students.removeEntity(nsUser);
    }

    private void checkValidation() {
        if (!SessionType.RECRUITING.isRecruiting(status)) {
            throw new IllegalArgumentException(NOT_RECRUITING_SESSION);
        }
    }

    public SessionDuration getSessionDuration() {
        return sessionDuration;
    }

    public String getTitle() {
        return title;
    }

    public void switchToPaidCourse() {
        free = false;
    }

    public void switchToFreeCourse() {
        free = true;
    }

    public void changeToStatus(SessionType status) {
        this.status = status;
    }

    public boolean getFree() {
        return free;
    }

    public SessionType getStatus() {
        return status;
    }
    public Long getId() {
        return id;
    }

    public String getImageFile() {
        return imageFile.getImgFile();
    }

    public boolean isFree() {
        return free;
    }

    public Students getStudents() {
        return students;
    }

    public Long getCourseId() {
        return courseId;
    }
}
