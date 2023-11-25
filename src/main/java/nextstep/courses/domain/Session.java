package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;

import java.util.Objects;

public class Session {

    private Duration duration;
    private Image image;
    private SessionStatus status;

    public static Session init(Duration duration, Image image) {
        return new Session(duration, image, SessionStatus.READY);
    }

    protected Session(Duration duration, Image image, SessionStatus status) {
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
