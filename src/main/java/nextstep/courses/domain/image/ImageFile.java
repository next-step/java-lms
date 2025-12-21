package nextstep.courses.domain.image;

public class ImageFile {

    private static final long MAX_IMAGE_SIZE_BYTES = 1024 * 1024;

    private final String fileName;
    private final long imageSize;
    private final ImageType imageType;

    public ImageFile(String fileName, long imageSize) {
        this(fileName, imageSize, ImageType.extractType(fileName));
    }

    public ImageFile(String fileName, long imageSize, ImageType imageType) {
        validateSize(imageSize);
        this.fileName = fileName;
        this.imageSize = imageSize;
        this.imageType = imageType;
    }

    private void validateSize(long imageSize) {
        if (imageSize > MAX_IMAGE_SIZE_BYTES) {
            throw new RuntimeException("이미지 크기는 1MB 이하여야 합니다.");
        }
    }
}
