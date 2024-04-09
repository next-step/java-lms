package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionDate {
    private LocalDate startAt;
    private LocalDate endAt;

    public SessionDate(LocalDate startAt, LocalDate endAt) {
        if (endAt.isBefore(startAt)) {
            throw new IllegalArgumentException("종료일자가 시작일자보다 빠릅니다");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof SessionDate))
            return false;
        SessionDate that = (SessionDate) object;
        return Objects.equals(startAt, that.startAt) && Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startAt, endAt);
    }
}
