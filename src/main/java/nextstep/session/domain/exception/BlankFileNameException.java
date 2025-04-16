package nextstep.session.domain.exception;

public class BlankFileNameException extends RuntimeException {
    public BlankFileNameException(String fileName) {
        super("파일 이름은 공백이 될 수 없습니다. 파일명=[" + fileName + "]");
    }
}
