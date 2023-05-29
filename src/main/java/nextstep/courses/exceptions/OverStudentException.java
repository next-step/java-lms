package nextstep.courses.exceptions;

public class OverStudentException extends RuntimeException {

    public OverStudentException(String message) {
        super(message);
    }

    public OverStudentException() {
        this("최대 수강인원을 초과할 수 없습니다.");
    }
}
