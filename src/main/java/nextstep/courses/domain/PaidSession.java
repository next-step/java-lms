package nextstep.courses.domain;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    private final int capacity;
    private final Long fee;

    public PaidSession(
            Course course,
            SessionImage sessionImage,
            LocalDateTime startTime,
            LocalDateTime endTime,
            SessionStatus sessionStatus,
            SessionType sessionType,
            int capacity,
            Long fee
    ) {
        super(course, sessionImage, startTime, endTime, sessionStatus, sessionType);
        this.capacity = capacity;
        this.fee = fee;
    }
}
