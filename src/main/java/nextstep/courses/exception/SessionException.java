package nextstep.courses.exception;

public class SessionException extends IllegalArgumentException{
    public SessionException() {
    }

    public SessionException(String message) {
        super(message);
    }
    public static class SessionNotOpenException extends SessionException {
        public SessionNotOpenException(String message) {
            super(message);
        }
    }
    public static class SessionFullException extends SessionException {
        public SessionFullException(String message) {
            super(message);
        }
    }

    public static class SessionFeeNotEqualException extends SessionException {
        public SessionFeeNotEqualException(String message) {
            super(message);
        }
    }
}

