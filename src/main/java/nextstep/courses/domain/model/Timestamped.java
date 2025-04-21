package nextstep.courses.domain.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Timestamped {
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
