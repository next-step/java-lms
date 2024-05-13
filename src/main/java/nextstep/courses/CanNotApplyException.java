package nextstep.courses;

public class CanNotApplyException extends Exception{
    private static final long serialVersionUID = 1L;

    public CanNotApplyException(String message) {
        super(message);
    }
}
