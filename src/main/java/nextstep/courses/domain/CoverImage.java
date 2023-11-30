package nextstep.courses.domain;

import nextstep.courses.exception.ImageOverVolumeException;

public class CoverImage {
    private long volume;
    private String type;
    private int height;
    private int width;

    public CoverImage(long volume, String type, int height, int width) {
        if (volume > 1000_000L) {
            throw new ImageOverVolumeException("이미지 크기는 1MB 이하여야 합니다");
        }
        this.volume = volume;
        this.type = type;
        this.height = height;
        this.width = width;
    }
}
