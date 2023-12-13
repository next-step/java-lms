package nextstep.courses.exception;

public class EmptyCoverImageException extends RuntimeException {

    public EmptyCoverImageException() {
        super("강의는 하나 이상의 커버 이미지를 가져야 합니다.");
    }
}
