package nextstep.courses.domain.image;

public class SessionImage {
    private static final long MAX_FILE_SIZE = 1024 * 1024;

    private final long fileSize;
    private final ImageType imageType;
    private final ImageDimension imageDimension;

    public SessionImage(long fileSize, String imageType, int width, int height) {
        this(fileSize, imageType, new ImageDimension(width, height));
    }

    public SessionImage(long fileSize, String imageType, ImageDimension imageDimension) {
        this(fileSize, ImageType.from(imageType), imageDimension);
    }

    public SessionImage(long fileSize, ImageType imageType, ImageDimension imageDimension) {
        validateFileSize(fileSize);
        this.fileSize = fileSize;
        this.imageType = imageType;
        this.imageDimension = imageDimension;
    }

    private void validateFileSize(long fileSize) {
        if (fileSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다");
        }
    }


    public int getWidth() {
        return imageDimension.getWidth();
    }

    public int getHeight() {
        return imageDimension.getHeight();
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getImageTypeValue() {
        return imageType.getValue();
    }
}
