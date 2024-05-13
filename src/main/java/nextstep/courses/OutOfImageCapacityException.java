package nextstep.courses;

public class OutOfImageCapacityException extends Exception{
    private static final long serialVersionUID = 1L;

    public OutOfImageCapacityException(String message) {
        super(message);
    }
}
