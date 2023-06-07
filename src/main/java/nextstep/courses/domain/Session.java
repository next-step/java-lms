package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {
    private static final String NOT_RECRUITING_SESSION = "모집중인 강이가 아닙니다.";

    private Long id;
    private SessionDuration sessionDuration;
    private ImgFile imageFile;
    private boolean free = false;
    private SessionType status = SessionType.READY;
    private Students students;

    public Session(Long id, String imageFile, LocalDate startedAt, LocalDate endedAt) {
        this.id = id;
        this.students = new Students();
        this.imageFile = new ImgFile(imageFile);
        sessionDuration = new SessionDuration(startedAt, endedAt);
    }

    public void putStudent(NsUser nsUser) {
        checkValidation();
        students.putEntity(nsUser);
    }

    public void removeStudent(NsUser nsUser) {
        students.removeEntity(nsUser);
    }

    private void checkValidation() {
        if (!SessionType.RECRUITING.isRecruiting(status)) {
            throw new IllegalArgumentException(NOT_RECRUITING_SESSION);
        }
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
}
