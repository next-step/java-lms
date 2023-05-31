package nextstep.courses.exception;

public class SessionUserNotFoundException extends RuntimeException {
  public SessionUserNotFoundException(String message) {
    super(message);
  }
}
