package nextstep.courses;

public class OverNumberOfStudentsException extends RuntimeException{

    public static final String MESSAGE = "학생 수가 초과되었습니다.";
    public OverNumberOfStudentsException() {
    }

    public OverNumberOfStudentsException(String message) {
        super(message);
    }
}
