package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {
    private final String title;
    private final Image image;
    private final SessionPeriod sessionPeriod;
    private final Enrollment enrollment;
    private final SessionType sessionType;
    private final int price;

    public Session(String title, int size, String imageType,
                   int width, int height,
                   LocalDate startedAt, LocalDate endedAt,
                   String state, String sessionType,
                   int capacity, int price) {
        this(title, new Image(size, imageType, width, height),
                new SessionPeriod(startedAt, endedAt), new Enrollment(SessionState.of(state), capacity),
                SessionType.of(sessionType), price);
    }

    public Session(String title, Image image,
                   SessionPeriod sessionPeriod, Enrollment enrollment,
                   SessionType sessionType, int price ) {
        this.title = title;
        this.image = image;
        this.sessionPeriod = sessionPeriod;
        this.enrollment = enrollment;
        this.sessionType = sessionType;
        this.price = price;
    }

    public void enroll(NsUser loginUser) {
        enrollment.enroll(loginUser);
    }
}

