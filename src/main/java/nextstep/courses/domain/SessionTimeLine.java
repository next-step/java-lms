package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionTimeLine {
    private final LocalDateTime createdAt;
    private final LocalDateTime closedAt;

    public SessionTimeLine(LocalDateTime createdAt, LocalDateTime closedAt) {
        validateSessionTime(createdAt, closedAt);
        this.createdAt = createdAt;
        this.closedAt = closedAt;
    }

    private static void validateSessionTime(LocalDateTime createdAt, LocalDateTime closedAt) {
        if (createdAt.isAfter(closedAt)) {
            throw new IllegalArgumentException("강의 시작일 및 종료일을 잘못 입력하셨습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionTimeLine that = (SessionTimeLine) o;
        return Objects.equals(createdAt, that.createdAt) && Objects.equals(closedAt, that.closedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, closedAt);
    }
}
