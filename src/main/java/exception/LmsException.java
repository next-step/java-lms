package exception;

public class LmsException extends RuntimeException {

  private final ExceptionCode exceptionCode;

  public LmsException(ExceptionCode exceptionCode) {
    super(exceptionCode.getDefaultMessage());
    this.exceptionCode = exceptionCode;
  }

  public LmsException(ExceptionCode exceptionCode, String message) {
    super(message);
    this.exceptionCode = exceptionCode;
  }

  public ExceptionCode getExceptionCode() {
    return exceptionCode;
  }
}
