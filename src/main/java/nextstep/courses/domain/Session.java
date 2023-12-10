package nextstep.courses.domain;

import java.util.Map;

public class Session {

    private Long id;

    private Image coverImage;

    private Period sessionPeriod;

    private SessionType type;

    public Session() {
    }

    public Session(Image image, Period period, SessionType type) {
        this.coverImage = image;
        this.sessionPeriod = period;
        this.type = type;
    }

    public void setCoverImage(Image image, Period sessionPeriod) {
        this.coverImage = image;
        this.sessionPeriod = sessionPeriod;
    }

    public Image getImage() {
        return this.coverImage;
    }

    public Period getPeriod() {
        return this.sessionPeriod;
    }

    public SessionType getType() {
        return this.type;
    }
}
