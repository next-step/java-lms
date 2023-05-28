package nextstep.courses.exceptions;

public class InvalidTermsException extends RuntimeException {

    public InvalidTermsException(String message) {
        super(message);
    }

    public InvalidTermsException() {
        this("수강기간은 종료일이 시작일보다 이후여야 합니다.");
    }
}
