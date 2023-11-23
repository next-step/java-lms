package nextstep.courses;

public class CannotVolumeOver1MBException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public CannotVolumeOver1MBException(String message) {
        super(message);
    }
}
