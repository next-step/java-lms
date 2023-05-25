package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;

    public SessionPeriod(LocalDateTime startedAt, LocalDateTime endedAt) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        validatePeriod();
    }

    private void validatePeriod()
    {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("시작일 : " + startedAt + ", 종료일 : "  + endedAt +", 종료일이 시작일보다 먼저 올 수 없습니다.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startedAt, that.startedAt) && Objects.equals(endedAt, that.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startedAt, endedAt);
    }
}
