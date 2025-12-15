package nextstep.sessions.domain;

public class SessionImage {

    private final String fileName;
    private final ImageSize imageSize;
    private final ImageDimension imageDimension;
    private final ImageType type;

    SessionImage(String fileName, long size, int width, int height) {
        this(fileName, new ImageSize(size), new ImageDimension(width, height));
    }

    public SessionImage(String fileName, ImageSize imageSize, ImageDimension imageDimension) {
        validateFileName(fileName);
        this.fileName = fileName;
        this.imageSize = imageSize;
        this.imageDimension = imageDimension;
        this.type = extractType(fileName);
    }

    public String fileName() {
        return fileName;
    }

    public long size() {
        return imageSize.value();
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("파일명은 빈 값일 수 없습니다");
        }
    }

    private ImageType extractType(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
        return ImageType.from(ext);
    }

}
