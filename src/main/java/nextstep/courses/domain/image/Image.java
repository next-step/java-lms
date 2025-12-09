package nextstep.courses.domain.image;

public class Image {
    private final ImageSize size;
    private final ImageDimension imageDimension;
    private final ImageFileExtension fileExtension;

    public Image(int size, int width, int height, String fileExtension) {
        this.size = new ImageSize(size);
        this.imageDimension = new ImageDimension(width, height);
        this.fileExtension = ImageFileExtension.from(fileExtension);
    }
}
