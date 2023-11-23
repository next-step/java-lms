package nextstep.courses;

public class VolumeOverException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public VolumeOverException(String message) {
        super(message);
    }
}
