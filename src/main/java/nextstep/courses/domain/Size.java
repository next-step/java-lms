package nextstep.courses.domain;

public class Size {

    private final int value;

    public Size(int value) {
        if ( value > 1024) {
            throw new IllegalArgumentException("이미지 파일 크기는 1MB 이하여야 합니다.");
        }
        this.value = value;
    }
}
