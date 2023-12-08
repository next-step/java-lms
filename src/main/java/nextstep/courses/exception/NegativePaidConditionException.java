package nextstep.courses.exception;

public class NegativePaidConditionException extends RuntimeException {
    public NegativePaidConditionException(int maxStudents, Long fee) {
        super("음수 값은 허용되지 않습니다. maxStudents : " + maxStudents + " fee : " + fee);
    }
}
