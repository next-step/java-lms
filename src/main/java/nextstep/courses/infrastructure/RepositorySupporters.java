package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RepositorySupporters {

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
