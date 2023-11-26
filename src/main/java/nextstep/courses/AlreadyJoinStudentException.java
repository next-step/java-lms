package nextstep.courses;

public class AlreadyJoinStudentException extends RuntimeException{
    public AlreadyJoinStudentException(String message) {
        super(message);
    }
}
