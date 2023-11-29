package nextstep.courses.domain;

import nextstep.courses.exception.image.ImageFileSizeExceededException;
import nextstep.courses.exception.image.ImageSizeBelowMinException;
import nextstep.courses.exception.image.UnsupportedImageRatioException;
import nextstep.courses.type.ImageType;

public class Image {
    private static final long FILE_SIZE_MAX = 1024 * 1;

    private static final long WIDTH_MIN = 300;
    private static final long HEIGHT_MIN = 200;

    private static final long WIDTH_RATIO = 3;
    private static final long HEIGHT_RATIO = 2;


    private final ImageType type;
    private final long width;
    private final long height;
    private final long fileSize;

    public Image(String type, long width, long height, long fileSize) {
        validate(width, height, fileSize);
        this.type = ImageType.from(type);
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
    }

    private void validate(long width, long height, long fileSize) {
        if(fileSize > FILE_SIZE_MAX){
            throw new ImageFileSizeExceededException("파일 사이즈는 " + FILE_SIZE_MAX + "KB 이하여야 합니다." );
        }

        if(width < WIDTH_MIN || height < HEIGHT_MIN){
            throw new ImageSizeBelowMinException("이미지 사이즈는 width/height=" + WIDTH_MIN + "/" + HEIGHT_MIN + "px 이상이어야 합니다.");
        }

        if(!allowedRatio(width, height)){
            throw new UnsupportedImageRatioException("이미지 비율은 " + WIDTH_RATIO + ":" + HEIGHT_RATIO + "이어야 합니다.");
        }
    }

    public static Image of(String type, long width, long height, long fileSize) {
        return new Image(type, width, height, fileSize);
    }

    private boolean allowedRatio(long width, long height) {
        return width * HEIGHT_RATIO == height * WIDTH_RATIO;
    }
}
