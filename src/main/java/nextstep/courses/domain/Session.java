package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;

import java.util.Objects;

public class Session {

    protected Long id;
    private Duration duration;
    private Image image;
    private SessionStatus status;

    public static Session init(Long id, Duration duration, Image image) {
        return new Session(id, duration, image, SessionStatus.READY);
    }

    protected Session(Long id, Duration duration, Image image, SessionStatus status) {
        this.id = id;
        this.duration = duration;
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
