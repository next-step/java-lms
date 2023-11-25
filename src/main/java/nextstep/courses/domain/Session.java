package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;

import java.time.LocalDate;

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

}
