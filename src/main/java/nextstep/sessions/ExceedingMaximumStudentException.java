package nextstep.sessions;

public class ExceedingMaximumStudentException extends RuntimeException {

    public ExceedingMaximumStudentException() {
        super("최대 학생수를 초과하였습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
