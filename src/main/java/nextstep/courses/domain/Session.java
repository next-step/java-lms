package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public abstract class Session {

    protected final Long id;

    protected final SessionInformation information;

    protected final Image image;

    protected Session(Long id, SessionInformation information, Image image) {
        this.id = id;
        this.information = information;
        this.image = image;
    }

    public abstract Attendee enroll(Payment payment, NsUser nsUser, Attendees attendees);

    protected Attendee checkAlreadyEnrolled(NsUser nsUser, Attendees attendees) {
        Attendee attendee = new Attendee(nsUser, this);
        attendees.contains(attendee);
        return attendee;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id)
                && Objects.equals(information, session.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, information, image);
    }
}
