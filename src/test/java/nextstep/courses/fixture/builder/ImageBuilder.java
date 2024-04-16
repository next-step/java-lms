package nextstep.courses.fixture.builder;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;

public class ImageBuilder {
    private ImageSize size;
    private ImageType type;
    private ImageWidth width;
    private ImageHeight height;

    private ImageBuilder() {}

    public static ImageBuilder anImage() {
        return new ImageBuilder();
    }

    public ImageBuilder withSize(ImageSize size) {
        this.size = size;
        return this;
    }

    public ImageBuilder withType(ImageType type) {
        this.type = type;
        return this;
    }

    public ImageBuilder withWidth(ImageWidth width) {
        this.width = width;
        return this;
    }

    public ImageBuilder withHeight(ImageHeight height) {
        this.height = height;
        return this;
    }

    public Image build() {
        return new Image(size, type, width, height);
    }
}
