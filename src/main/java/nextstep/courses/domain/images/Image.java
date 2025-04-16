package nextstep.courses.domain.images;

public class Image {

    private ImageType type;

    // KB
    private ImageSizeKb size;

    private ImageDimension dimension;

    public Image() {
    }

    public Image(Builder builder) {
        this.type = builder.type;
        this.size = builder.size;
        this.dimension = builder.dimension;
    }

    public Image(ImageType type, double size, double width, double height) {
        this.type = type;
        this.size = new ImageSizeKb(size);
        this.dimension = new ImageDimension(width, height);
    }

    public static class Builder {
        private ImageType type;
        private ImageSizeKb size;
        private ImageDimension dimension;

        public Builder type(ImageType type) {
            this.type = type;
            return this;
        }

        public Builder size(double size) {
            this.size = new ImageSizeKb(size);
            return this;
        }

        public Builder dimension(double width, double height) {
            this.dimension = new ImageDimension(width, height);
            return this;
        }

        public Image build() {
            return new Image(this);
        }
    }
}
