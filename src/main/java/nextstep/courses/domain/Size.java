package nextstep.courses.domain;

public class Size {

    public static final int MAX_SIZE = 1024;
    private final int value;

    public Size(int value) {
        if ( value > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 파일 크기는 1MB 이하여야 합니다.");
        }
        this.value = value;
    }
}
