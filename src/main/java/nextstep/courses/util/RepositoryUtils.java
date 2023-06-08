package nextstep.courses.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RepositoryUtils {

    private RepositoryUtils() {

    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
