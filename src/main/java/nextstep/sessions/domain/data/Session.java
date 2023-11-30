package nextstep.sessions.domain.data;

import java.time.LocalDateTime;

import nextstep.sessions.domain.data.type.SessionFee;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.CoverImage;
import nextstep.sessions.domain.data.vo.Duration;

public class Session {

    private Long id;
    private final String name;
    private final SessionFee fee;
    private final SessionState sessionState;
    private final CoverImage coverImage;
    private final Duration duration;

    public Session(String name, SessionFee fee, SessionState sessionState, CoverImage coverImage, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.fee = fee;
        this.sessionState = sessionState;
        this.coverImage = coverImage;
        this.duration = new Duration(startDate, endDate);
    }
}
