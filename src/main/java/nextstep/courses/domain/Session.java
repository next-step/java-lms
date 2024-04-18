package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private CoverImage coverImage;

    private SessionType sesstionType;

    private SessionStatus sessionStatus;

    private boolean isEnroll = false;


    public Session(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        if (sessionStatus == SessionStatus.ENROLLING) {
            isEnroll = true;
        }
    }

    public boolean isEnroll() {
        return this.isEnroll;
    }
}
