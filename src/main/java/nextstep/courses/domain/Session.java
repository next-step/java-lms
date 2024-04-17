package nextstep.courses.domain;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Course course;
    private final SessionImage sessionImage;
    private final SessionStatus sessionStatus;
    private final SessionType sessionType;
    private final List<NsUser> students = new ArrayList<>();

    public Session(
            Course course,
            SessionImage sessionImage,
            LocalDateTime startTime,
            LocalDateTime endTime,
            SessionStatus sessionStatus,
            SessionType sessionType
    ) {
        this.course = course;
        this.sessionImage = sessionImage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }
}
