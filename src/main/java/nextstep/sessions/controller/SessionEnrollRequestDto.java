package nextstep.sessions.controller;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class SessionEnrollRequestDto {
    private final LocalDateTime requestDatetime = LocalDateTime.now();
    private final Long sessionId;

    public SessionEnrollRequestDto(Long sessionId) {
        Assert.notNull(sessionId, "강의 ID는 필수 입력값입니다.");

        this.sessionId = sessionId;
    }

    public Long sessionId() {
        return sessionId;
    }

    public LocalDateTime requestDateTime() {
        return requestDatetime;
    }
}
