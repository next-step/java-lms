package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {
    private long id;
    private final String title;
    private final Image image;
    private final SessionPeriod sessionPeriod;
    private final Enrollment enrollment;
    private final SessionType sessionType;
    private final int price;

    public Session(long id, String title, int size, String imageType,
                   int width, int height,
                   LocalDate startedAt, LocalDate endedAt,
                   String state, String sessionType,
                   int capacity, int price) {
        this(id, title, new Image(size, imageType, width, height),
                new SessionPeriod(startedAt, endedAt), new Enrollment(SessionState.of(state), capacity),
                SessionType.of(sessionType), price);
    }

    public Session(long id, String title, Image image,
                   SessionPeriod sessionPeriod, Enrollment enrollment,
                   SessionType sessionType, int price ) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.sessionPeriod = sessionPeriod;
        this.enrollment = enrollment;
        this.sessionType = sessionType;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Image getImage() {
        return image;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public int getPrice() {
        return price;
    }

    public void enroll(NsUser loginUser) {
        enrollment.enroll(loginUser);
    }
}

