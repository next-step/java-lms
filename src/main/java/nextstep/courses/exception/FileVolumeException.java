package nextstep.courses.exception;

public class FileVolumeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FileVolumeException(String message) {
        super(message);
    }
}
