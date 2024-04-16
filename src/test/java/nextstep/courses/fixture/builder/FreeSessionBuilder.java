package nextstep.courses.fixture.builder;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.impl.FreeSession;

public class FreeSessionBuilder {
    private SessionName name;
    private LocalDateTime beginDateTime;
    private LocalDateTime endDateTime;
    private Image image;
    private SessionStatus status;

    private FreeSessionBuilder() {}

    public static FreeSessionBuilder anFreeSession() {
        return new FreeSessionBuilder();
    }

    public FreeSessionBuilder withName(String name) {
        this.name = new SessionName(name);
        return this;
    }

    public FreeSessionBuilder withBeginDateTime(LocalDateTime beginDateTime) {
        this.beginDateTime = beginDateTime;
        return this;
    }

    public FreeSessionBuilder withEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public FreeSessionBuilder withImage(Image image) {
        this.image = image;
        return this;
    }

    public FreeSessionBuilder withStatus(SessionStatus status) {
        this.status = status;
        return this;
    }

    public FreeSession build() {
        return new FreeSession(name, beginDateTime, endDateTime, image, status);
    }
}
