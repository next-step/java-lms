package nextstep.sessions.domain;

public class Session {
    private Long id;
    private String title;
    private SessionType type;
    private SessionStatus status;
    private SessionPeriod period;
    private Image coverImage;
    private EnrollmentCapacity enrollmentCapacity;
    private Long price;

    public Session(Long price) {
        this.price = price;
    }

    public Session(SessionStatus status, Long price) {
        this.status = status;
        this.price = price;
    }

    public Session(Long id, String title, SessionType type, SessionStatus status, SessionPeriod period, Image coverImage, EnrollmentCapacity enrollmentCapacity, Long price) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.status = status;
        this.period = period;
        this.coverImage = coverImage;
        this.enrollmentCapacity = enrollmentCapacity;
        this.price = price;
    }

    public Long price() {
        return this.price;
    }

    public boolean isRecruiting() {
        return status.equals(SessionStatus.RECRUITING);
    }
}
