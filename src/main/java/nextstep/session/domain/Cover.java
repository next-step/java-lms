package nextstep.session.domain;

public class Cover {

    public static final int SIZE_UNIT = 1024;
    public static final int MAXIMUM_MEGABYTE_SIZE = 1;
    private final Resolution resolution;
    private final ImageFilePath imageFilePath;
    private final long byteSize;

    public Cover(Resolution resolution, ImageFilePath imageFilePath, long byteSize) {
        validate(byteSize);

        this.resolution = resolution;
        this.imageFilePath = imageFilePath;
        this.byteSize = byteSize;
    }

    private void validate(long byteSize) {
        if ((byteSize / SIZE_UNIT / SIZE_UNIT) > MAXIMUM_MEGABYTE_SIZE) {
            throw new IllegalArgumentException("이미지는 1MB 이하여야 합니다.");
        }
    }
}
