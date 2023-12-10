package nextstep.courses.domain;

import java.util.Map;

public class Session {

    private Long id;

    private Image coverImage;

    private Period sessionPeriod;

    public Session() {
    }

    public Session(Image image, Period period) {
        this.coverImage = image;
        this.sessionPeriod = period;
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
}
