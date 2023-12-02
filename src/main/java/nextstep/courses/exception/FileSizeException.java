package nextstep.courses.exception;

public class FileSizeException extends IllegalArgumentException{
    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }
}
