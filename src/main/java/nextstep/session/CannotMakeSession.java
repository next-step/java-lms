package nextstep.session;

public class CannotMakeSession extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public CannotMakeSession(String message) {
    super(message);
  }
}
