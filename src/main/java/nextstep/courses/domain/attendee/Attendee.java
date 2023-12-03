package nextstep.courses.domain.attendee;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Attendee {

    private final Long id;

    private final NsUser nsUser;

    private final Session session;

    public Attendee(Long id, NsUser nsUser, Session session) {
        this.id = id;
        this.nsUser = nsUser;
        this.session = session;
    }

    public Attendee(NsUser nsUser, Session session) {
        this.id = 0L;
        this.nsUser = nsUser;
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(id, attendee.id)
                && Objects.equals(nsUser, attendee.nsUser)
                && Objects.equals(session, attendee.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nsUser, session);
    }
}
