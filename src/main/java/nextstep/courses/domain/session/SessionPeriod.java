package nextstep.courses.domain.session;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class SessionPeriod {

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public SessionPeriod() {
        this(LocalDateTime.now(), LocalDateTime.now());
    }

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
