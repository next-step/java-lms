package utils;

import exception.ExceptionCode;
import exception.LmsException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

public class AssertionUtils {
  private AssertionUtils() {};

  public static void assertThatThrowsLmsException(ThrowingCallable executable, ExceptionCode exceptionCode) {
    Assertions.assertThatThrownBy(executable)
        .isInstanceOf(LmsException.class)
        .extracting("exceptionCode")
        .isEqualTo(exceptionCode);
  }
}
