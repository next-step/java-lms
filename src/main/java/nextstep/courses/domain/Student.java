package nextstep.courses.domain;

import nextstep.common.domain.BaseControlField;

import java.time.LocalDateTime;

public class Student extends BaseControlField {
    private int sessionId;
    private Long userId;

    public Student(int sessionId, Long userId) {
        super(userId, LocalDateTime.now(), null);
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }
}
