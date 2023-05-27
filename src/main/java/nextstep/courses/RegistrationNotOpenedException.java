package nextstep.courses;

public class RegistrationNotOpenedException extends RuntimeException {

  public RegistrationNotOpenedException(String message) {
    super(message);
  }
}
