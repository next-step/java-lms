package nextstep.courses.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import nextstep.users.domain.NsUser;

@Builder
@AllArgsConstructor
public class Session {

    private Long id;
    private String title;
    private Period period;
    private String imageUrl;
    private SessionType sessionType;
    private SessionValidator sessionValidator;

    public Session(Long id, String title, int maxCount, Period period, String imageUrl, SessionState sessionState,
            SessionType sessionType) {
        this.id = id;
        this.title = title;
        this.sessionValidator = new SessionValidator(maxCount, sessionState);
        this.period = period;
        this.imageUrl = imageUrl;
        this.sessionType = sessionType;
    }

    public void apply(NsUser loginUser) {
        sessionValidator.addPerson(loginUser);
    }
}
