package utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeUtils {

  private LocalDateTimeUtils() {}

  public static LocalDateTime of(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
