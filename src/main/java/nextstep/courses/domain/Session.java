package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private Duration duration;
    private Image image;

    public Session(LocalDate start, LocalDate end, Image image) {
        this.duration = new Duration(start, end);
        this.image = image;
    }

    public Image image() {
        return this.image;
    }

}
