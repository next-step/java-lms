package nextstep.session;

public class StudentNumberExceededException extends RuntimeException {

    public StudentNumberExceededException(String message) {
        super(message);
    }
}
