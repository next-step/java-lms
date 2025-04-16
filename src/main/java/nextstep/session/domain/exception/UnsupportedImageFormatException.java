package nextstep.session.domain.exception;

public class UnsupportedImageFormatException extends RuntimeException {
    public UnsupportedImageFormatException(String format) {
        super("지원되지 않는 이미지 포멧입니다. foramt=" + format);
    }
}
