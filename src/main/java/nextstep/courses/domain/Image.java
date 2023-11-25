package nextstep.courses.domain;

import nextstep.courses.ImageSizeOverException;

public class Image {

    private final long size;

    public Image(long size) {
        this.size = size;
        validate();
    }

    public void validate() {
        if (this.size > 1) {
            throw new ImageSizeOverException("이미지 최대 크기를 초과했습니다.");
        }
    }

}
