package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionDuration {

    private final Long id;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public SessionDuration(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static SessionDuration of(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return new SessionDuration(id, startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionDuration that = (SessionDuration) o;
        return Objects.equals(id, that.id) && Objects.equals(startDate,
            that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate);
    }
}
