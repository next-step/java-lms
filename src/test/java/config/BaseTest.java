package config;

import exception.ExceptionCode;
import exception.LmsException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

public abstract class BaseTest {
  protected void assertThatThrowsLmsException(ThrowingCallable executable, ExceptionCode exceptionCode) {
    Assertions.assertThatThrownBy(executable)
        .isInstanceOf(LmsException.class)
        .extracting("exceptionCode")
        .isEqualTo(exceptionCode);
  }
}
