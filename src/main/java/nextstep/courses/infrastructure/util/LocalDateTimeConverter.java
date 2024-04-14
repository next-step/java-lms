package nextstep.courses.infrastructure.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeConverter {

    public static LocalDateTime convert(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
