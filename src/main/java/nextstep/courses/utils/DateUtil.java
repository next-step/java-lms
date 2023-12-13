package nextstep.courses.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateUtil {
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
