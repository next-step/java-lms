package nextstep.courses.domain.image;

public class CoverImage {

    private final ImageInfo imageInfo;
    private final FileExtension extension;


    public CoverImage(ImageInfo imageInfo, String extension) {
        this.imageInfo = imageInfo;
        this.extension = new FileExtension(extension);
    }
}
