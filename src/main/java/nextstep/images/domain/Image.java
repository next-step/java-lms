package nextstep.images.domain;

public class Image {

    private Long id;

    private ImageType type;

    // KB
    private ImageSizeKb size;

    private ImageDimension dimension;

    public Image(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.size = builder.size;
        this.dimension = builder.dimension;
    }

    public Long getId() {
        return id;
    }

    public ImageType getType() {
        return type;
    }

    public ImageSizeKb getSize() {
        return size;
    }

    public ImageDimension getDimension() {
        return dimension;
    }

    public static class Builder {
        private Long id;
        private ImageType type;
        private ImageSizeKb size;
        private ImageDimension dimension;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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
