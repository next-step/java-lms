package nextstep.courses.exception;

public class ExceedLimitedVolumeException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.EXCEED_LIMITED_VOLUME.getMessage();

    public ExceedLimitedVolumeException() {
        this(MESSAGE);
    }

    public ExceedLimitedVolumeException(String s) {
        super(s);
    }
}
