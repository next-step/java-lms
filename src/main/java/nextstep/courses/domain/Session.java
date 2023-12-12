package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class Session {
    private String title;
    private SessionType type;
    private int price;
    private Enrollment enrollment;
    private SessionPeriod sessionPeriod;
    private Long createId;
    private String image;

    public Session(String title, SessionType type, int price, Enrollment enrollment, LocalDate startDate, LocalDate endDate, Long createId, String image) {
        this(title, type, price, enrollment, new SessionPeriod(startDate, endDate), createId, image);
    }
    public Session(String title, SessionType type, int price, Enrollment enrollment, SessionPeriod sessionPeriod, Long createId, String image) {
        type.valid(price);
        this.title = title;
        this.type = type;
        this.price = price;
        this.enrollment = enrollment;
        this.sessionPeriod = sessionPeriod;
        this.createId = createId;
        this.image = image;

    }

    public void enroll(NsUser nsUser) {
        enrollment.enroll(nsUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return price == session.price && Objects.equals(title, session.title) && type == session.type && Objects.equals(enrollment, session.enrollment) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(createId, session.createId) && Objects.equals(image, session.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, price, enrollment, sessionPeriod, createId, image);
    }
}
