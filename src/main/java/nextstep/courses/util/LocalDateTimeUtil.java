package nextstep.courses.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeUtil {

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
