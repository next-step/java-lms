package nextstep.courses.domain;

import java.util.Collections;
import java.util.List;

public class SessionImages {

    private final List<SessionImage> sessionImages;

    private SessionImages(List<SessionImage> sessionImages) {
        this.sessionImages = sessionImages;
    }

    public static SessionImages of(List<SessionImage> sessionImages) {
        return new SessionImages(sessionImages);
    }

    public List<SessionImage> getSessionImages() {
        return Collections.unmodifiableList(this.sessionImages);
    }
}
