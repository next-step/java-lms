package nextstep.courses.exception;

public class FileException extends IllegalArgumentException{
    public FileException() {
    }

    public FileException(String message) {
        super(message);
    }
    public class FileSizeException extends FileException {
        public FileSizeException(String message) {
            super(message);
        }
    }
    public static class FileDimensionsException extends FileException {
        public FileDimensionsException(String message) {
            super(message);
        }
    }

    public static class FileExtensionException extends FileException {
        public FileExtensionException(String message) {
            super(message);
        }
    }
}

