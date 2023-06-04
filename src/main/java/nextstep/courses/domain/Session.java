package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Session {
    private static final int MAX_STUDENTS = 20;
    private static final String MAX_STUDENT_EXCEPTION = "최대 인원을 초과하였습니다.";
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

    public void putStudent(Student student) {
        checkValidation();
        students.putEntity(student);
    }

    public void removeStudent(Student student) {
        students.removeEntity(student);
    }

    private void checkValidation() {
        if (students.getSize() > MAX_STUDENTS) {
            throw new IllegalArgumentException(MAX_STUDENT_EXCEPTION);
        }

        if (!SessionType.RECRUITING.equals(status)) {
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
