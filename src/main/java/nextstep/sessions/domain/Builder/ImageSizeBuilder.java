package nextstep.sessions.domain.Builder;

import nextstep.sessions.domain.ImageSize;

public class ImageSizeBuilder {
    private int size = 100;
    private double width = 300;
    private double height = 200;

    public ImageSizeBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public ImageSizeBuilder withWidth(double width) {
        this.width = width;
        return this;
    }

    public ImageSizeBuilder withHeight(double height) {
        this.height = height;
        return this;
    }

    public ImageSize build() {
        return new ImageSize(size, width, height);
    }
}
