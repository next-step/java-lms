package nextstep.session.domain;

public class Cover {

    public static final int SIZE_UNIT = 1024;
    public static final int MAXIMUM_MEGABYTE_SIZE = 1;
    private final Resolution resolution;
    private final FilePathInformation filePathInformation;
    private final long byteSize;

    public Cover(Resolution resolution, FilePathInformation filePathInformation, long byteSize) {
        validate(byteSize);

        this.resolution = resolution;
        this.filePathInformation = filePathInformation;
        this.byteSize = byteSize;
    }

    private void validate(long byteSize) {
        if ((byteSize / SIZE_UNIT / SIZE_UNIT) > MAXIMUM_MEGABYTE_SIZE) {
            throw new IllegalArgumentException("이미지는 1MB 이하여야 합니다.");
        }
    }
}
