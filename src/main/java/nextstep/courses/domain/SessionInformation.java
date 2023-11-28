package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;

import java.util.List;

public class SessionInformation {

    private final String title;
    private final Period period;
    private final List<Thumbnail> thumbnails;
    private final SessionStatus status;

    public SessionInformation(String title,
                              Period period,
                              List<Thumbnail> thumbnails,
                              SessionStatus status) {
        this.title = title;
        this.period = period;
        this.thumbnails = thumbnails;
        this.status = status;
    }
}
