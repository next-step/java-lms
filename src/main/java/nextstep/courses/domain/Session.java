package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private Image image;
    SessionType sessionType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Session() {
    }

    public Session(Image image) {
        this.image = image;
    }

    public Session(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Image getImage() {
        return image;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
