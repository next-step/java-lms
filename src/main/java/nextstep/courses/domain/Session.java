package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;

import java.time.LocalDate;
import java.util.Objects;

public class Session {

    private Duration duration;
    private Image image;
    private SessionStatus status;

    public Session(LocalDate start, LocalDate end, Image image, SessionStatus status) {
        this.duration = new Duration(start, end);
        this.image = image;
        this.status = status;
    }

    public Image image() {
        return this.image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(duration, session.duration) && Objects.equals(image, session.image) && status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, image, status);
    }
}
