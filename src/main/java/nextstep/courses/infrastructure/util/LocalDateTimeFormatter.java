package nextstep.courses.infrastructure.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeFormatter {
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
