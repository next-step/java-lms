package nextstep.sessions.domain.Builder;

import nextstep.sessions.domain.SessionImage;
import nextstep.sessions.domain.SessionImages;

import java.util.ArrayList;
import java.util.List;

public class SessionImagesBuilder {
    private List<SessionImage> images = new ArrayList<>();

    public SessionImagesBuilder withSessionImages(List<SessionImage> images) {
        this.images = images;
        return this;
    }

    public SessionImages build() {
        images.add(new SessionImageBuilder().build());
        return new SessionImages(images);
    }
}
