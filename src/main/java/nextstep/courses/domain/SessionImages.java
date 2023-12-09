package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionImages {
    private final List<SessionImage> sessionImages;

    public SessionImages(List<SessionImage> sessionImages) {
        this.sessionImages = new ArrayList<>(sessionImages);
    }

    public List<SessionImage> sessionImages() {
        return Collections.unmodifiableList(sessionImages);
    }
}
