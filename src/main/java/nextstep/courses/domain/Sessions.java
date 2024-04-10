package nextstep.courses.domain;

import java.util.List;

public class Sessions {

    private final Long courseId;

    private List<Sessions> sessions;

    public Sessions(Long courseId, List<Sessions> sessions) {
        this.courseId = courseId;
        this.sessions = sessions;
    }
}
