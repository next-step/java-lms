package nextstep.sessions.domain.data.dto;

import java.time.LocalDateTime;

import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.*;

public class SessionCreateRequestDto {

    private PaidType paidType;
    private long fee;
    private int capacity;
    private SessionRunningState sessionRunningStateState;
    private SessionRecruitingState sessionRecruitingState;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Session toSession() {
        return new Session(paidType, fee, capacity, sessionRunningStateState, sessionRecruitingState, startDate, endDate);
    }
}
