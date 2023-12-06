package nextstep.courses.domain.session;

import nextstep.courses.exception.image.ImageFileSizeExceededException;
import nextstep.courses.type.ImageType;

public class Image {
    private static final long FILE_SIZE_MAX = 1024;

    private final Long id;
    private final ImageType type;
    private final ImageSize imageSize;
    private final long fileSize;

    private Image(Long id, String type, long width, long height, long fileSize) {
        validate(fileSize);
        this.id = id;
        this.type = ImageType.from(type);
        this.imageSize = ImageSize.of(width, height);
        this.fileSize = fileSize;
    }

    private void validate(long fileSize) {
        if (fileSize > FILE_SIZE_MAX) {
            throw new ImageFileSizeExceededException("파일 사이즈는 " + FILE_SIZE_MAX + "KB 이하여야 합니다. 현재 파일 사이즈 : " + fileSize + "KB");
        }
    }

    public static Image from() {
        return new Image(null, "jpg", ImageSize.WIDTH_MIN, ImageSize.HEIGHT_MIN, FILE_SIZE_MAX);
    }

    public static Image of(String type, long width, long height, long fileSize) {
        return of(null, type, width, height, fileSize);
    }

    public static Image of(Long id, String type, long width, long height, long fileSize) {
        return new Image(id, type, width, height, fileSize);
    }

    public Long id() {
        return id;
    }

    public String type() {
        return type.name();
    }

    public long width() {
        return imageSize.width();
    }

    public long height() {
        return imageSize.height();
    }

    public long fileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", type=" + type +
                ", imageSize=" + imageSize +
                ", fileSize=" + fileSize +
                '}';
    }
}
