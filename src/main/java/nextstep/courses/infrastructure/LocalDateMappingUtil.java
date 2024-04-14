package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateMappingUtil {

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    public static LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
