package nextstep.courses.domain;

import java.time.LocalDate;

public enum SessionState {

    PREPARING("준비중", true),
    PROGRESSING("진행중", false),
    FINISH("종료", false);

    private final String desc;
    private final boolean availableManualChangeSession;


    SessionState(
            String desc,
            boolean availableManualChangeSession
    ) {
        this.desc = desc;
        this.availableManualChangeSession = availableManualChangeSession;
    }

    public SessionState syncSessionStateAccordingTo(LocalDate now, SessionPeriod sessionPeriod) {

        if (sessionPeriod.isAfterEndDate(now)) {
            return SessionState.FINISH;
        }

        if (sessionPeriod.isBeforeStartDate(now)) {
            return SessionState.PREPARING;
        }

        return SessionState.PROGRESSING;
    }


    public boolean isAvailableManualChangeSession() {
        return availableManualChangeSession;
    }

}
