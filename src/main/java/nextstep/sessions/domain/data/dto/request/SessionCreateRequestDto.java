package nextstep.sessions.domain.data.dto.request;

import java.time.LocalDateTime;

import nextstep.sessions.domain.data.session.*;

public class SessionCreateRequestDto {

    private Long id;

    private PaidType paidType;
    private long fee;
    private int capacity;
    private SessionRunningState sessionRunningStateState;
    private SessionRecruitingState sessionRecruitingState;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Session toSession() {
        return new Session(id, paidType, fee, capacity, sessionRunningStateState, sessionRecruitingState, startDate, endDate);
    }
}
