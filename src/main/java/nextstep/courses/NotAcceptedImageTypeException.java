package nextstep.courses;

public class NotAcceptedImageTypeException extends Exception{
    private static final long serialVersionUID = 1L;

    public NotAcceptedImageTypeException(String message) {
        super(message);
    }
}
