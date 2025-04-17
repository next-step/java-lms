package nextstep.images.domain;

public class Image {

    private Long id;

    private ImageType type;

    // KB
    private ImageSizeKb size;

    private ImageDimension dimension;

    public Image() {
    }

    public Image(Long id, ImageType type, double size, double width, double height) {
        this.id = id;
        this.type = type;
        this.size = new ImageSizeKb(size);
        this.dimension = new ImageDimension(width, height);
    }

    public Image(Builder builder) {
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
