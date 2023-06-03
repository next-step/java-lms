package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private static final int MAX_SESSION = 20;
    private CourseDuration courseDuration;
    private ImgFile imageFile;
    private boolean free = false;
    private SessionType status = SessionType.READY;
    private Students students;

    public Session(String imageFile, LocalDate startedAt, LocalDate endedAt, Students students) {
        checkValidation(students);
        this.imageFile = new ImgFile(imageFile);
        this.students = students;
        courseDuration = new CourseDuration(startedAt, endedAt);
    }

    private void checkValidation(Students students) {
        students.isValidNumberOfStudents(MAX_SESSION);
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
}
