package nextstep.courses.tobe.domain;

public class ProcessEndedException extends RuntimeException {

    public ProcessEndedException(String message) {
        super(message);
    }
}
