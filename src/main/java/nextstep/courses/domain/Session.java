package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private String title;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private String imageUrl;
    private SessionState sessionState;
    private SessionType sessionType;
    private SessionHeadCount sessionHeadCount;

    public Session(Long id, String title, int maxCount, LocalDateTime startDate,
            LocalDateTime endDate, String imageUrl, SessionState sessionState,
            SessionType sessionType) {
        this.id = id;
        this.title = title;
        this.sessionHeadCount = new SessionHeadCount(maxCount);
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.sessionState = sessionState;
        this.sessionType = sessionType;
    }

    public void apply(NsUser loginUser) {
        if (!SessionState.isRecruitable(sessionState)) {
            throw new IllegalArgumentException("해당 강의는 수강신청중이 아닙니다.");
        }

        sessionHeadCount.addPerson(loginUser);
    }
}
