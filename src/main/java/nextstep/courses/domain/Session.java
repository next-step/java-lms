package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class Session {
    private Long id;
    private String title;
    private SessionType type;
    private int price;
    private Enrollment enrollment;
    private SessionPeriod sessionPeriod;
    private Long creatorId;
    private String image;

    public Session(Long id, String title, SessionType type, int price, Enrollment enrollment, LocalDate startDate, LocalDate endDate, Long creatorId, String image) {
        this(id, title, type, price, enrollment, new SessionPeriod(startDate, endDate), creatorId, image);
    }
    public Session(Long id, String title, SessionType type, int price, Enrollment enrollment, SessionPeriod sessionPeriod, Long creatorId, String image) {
        type.valid(price);
        this.id = id;
        this.title = title;
        this.type = type;
        this.price = price;
        this.enrollment = enrollment;
        this.sessionPeriod = sessionPeriod;
        this.creatorId = creatorId;
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
        return price == session.price && Objects.equals(title, session.title) && type == session.type && Objects.equals(enrollment, session.enrollment) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(creatorId, session.creatorId) && Objects.equals(image, session.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, price, enrollment, sessionPeriod, creatorId, image);
    }
}
