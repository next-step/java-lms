package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;

public class SessionInformation {

    private final String title;
    private final Period period;
    private final Thumbnail thumbnail;
    private final SessionStatus status;

    public SessionInformation(String title,
                              Period period,
                              Thumbnail thumbnail,
                              SessionStatus status) {
        this.title = title;
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
    }
}
