package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private int studentCount;
    private int tuition;
    private SessionStatus sessionStatus;
    private Image image;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Session() {
    }

    public Session(int tuition) {
        this.tuition = tuition;
    }

    public Session(Image image) {
        this.image = image;
    }

    public Session(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Session(int studentCount, int tuition) {
        this.studentCount = studentCount;
        this.tuition = tuition;
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getTuition() {
        return tuition;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public Image getImage() {
        return image;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public SessionStatus isSessionStatus() {
        return sessionStatus;
    }
}
