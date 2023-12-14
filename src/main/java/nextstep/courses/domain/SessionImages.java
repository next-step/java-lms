package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class SessionImages {
    private List<SessionImage> images;

    public SessionImages(SessionImage... images) {
        this.images = new ArrayList<>(List.of(images));
    }
}
