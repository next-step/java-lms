package nextstep.courses.domain;

import java.util.Collections;
import java.util.List;

public class SessionImages {

    private final List<SessionImage> sessionImages;

    public SessionImages(List<SessionImage> sessionImages) {
        this.sessionImages = sessionImages;
    }

    public List<SessionImage> getSessionImages() {
        return Collections.unmodifiableList(this.sessionImages);
    }
}
