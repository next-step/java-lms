package nextstep.sessions.domain.Builder;

import nextstep.sessions.domain.ImageSize;
import nextstep.sessions.domain.ImageType;
import nextstep.sessions.domain.SessionImage;

public class SessionImageBuilder {
    private Long id = 1L;
    private ImageSize imageSize = new ImageSizeBuilder().build();
    private ImageType type = ImageType.PNG;

    public SessionImageBuilder withImageId(Long imageId) {
        this.id = imageId;
        return this;
    }

    public SessionImageBuilder withImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
        return this;
    }

    public SessionImageBuilder withImageType(ImageType imageType) {
        this.type = imageType;
        return this;
    }

    public SessionImage build() {
        return new SessionImage(id, imageSize, type);
    }
}
