package nextstep.courses.domain.session;

import nextstep.courses.exception.image.ImageSizeBelowMinException;
import nextstep.courses.exception.image.UnsupportedImageRatioException;

public class ImageSize {

    public static final long WIDTH_MIN = 300;
    public static final long HEIGHT_MIN = 200;

    private static final long WIDTH_RATIO = 3;
    private static final long HEIGHT_RATIO = 2;

    private final long width;
    private final long height;

    private ImageSize(long width, long height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(long width, long height) {
        if (width < WIDTH_MIN || height < HEIGHT_MIN) {
            throw new ImageSizeBelowMinException("이미지 사이즈는 width/height=" + WIDTH_MIN + "/" + HEIGHT_MIN + "px 이상이어야 합니다. 현재 width/heihgt : " + width + "/" + height);
        }

        if (!allowedRatio(width, height)) {
            throw new UnsupportedImageRatioException("이미지 비율은 " + WIDTH_RATIO + ":" + HEIGHT_RATIO + "이어야 합니다.");
        }
    }

    private boolean allowedRatio(long width, long height) {
        return width * HEIGHT_RATIO == height * WIDTH_RATIO;
    }

    public static ImageSize of(long width, long height) {
        return new ImageSize(width, height);
    }


    public long width() {
        return width;
    }

    public long height() {
        return height;
    }

    @Override
    public String toString() {
        return "ImageSize{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
