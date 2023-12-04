package nextstep.courses.domain.cource;

import nextstep.courses.exception.image.ImageFileSizeExceededException;
import nextstep.courses.exception.image.ImageSizeBelowMinException;
import nextstep.courses.exception.image.UnsupportedImageRatioException;
import nextstep.courses.type.ImageType;

public class Image {
    private static final long FILE_SIZE_MAX = 1024;

    private final ImageType type;
    private final ImageSize imageSize;
    private final long fileSize;

    public Image(String type, long width, long height, long fileSize) {
        validate(width, height, fileSize);
        this.type = ImageType.from(type);
        this.imageSize = ImageSize.of(width, height);
        this.fileSize = fileSize;
    }

    private void validate(long width, long height, long fileSize) {
        if(fileSize > FILE_SIZE_MAX){
            throw new ImageFileSizeExceededException("파일 사이즈는 " + FILE_SIZE_MAX + "KB 이하여야 합니다. 현재 파일 사이즈 : " + fileSize + "KB" );
        }
    }

    public static Image from() {
        return new Image("jpg", ImageSize.WIDTH_MIN, ImageSize.HEIGHT_MIN, FILE_SIZE_MAX);
    }

    public static Image of(String type, long width, long height, long fileSize) {
        return new Image(type, width, height, fileSize);
    }

}
