package nextstep.common;

import java.time.LocalDateTime;

public class BaseTimeEntity {

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public BaseTimeEntity(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt == null || endAt == null) {
            throw new IllegalArgumentException("시작 날짜와 종료 날짜는 비어있을 수 없습니다.");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
