package nextstep.session;

public class CannotApplySession extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public CannotApplySession(String message) {
    super(message);
  }
}
