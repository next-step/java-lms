package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private Long id;
    private Long courseId;
    private LocalDateTime from;
    private LocalDateTime to;
    private CoverImage coverImage;
    private SessionType sessionType;
    private SessionStatus status;
    private SessionRegister sessionRegister;

    public Session(
            Long id,
            Long courseId,
            LocalDateTime from,
            LocalDateTime to,
            CoverImage coverImage,
            SessionType sessionType,
            SessionStatus status,
            SessionRegister sessionRegister
    ) {
        this.id = id;
        this.courseId = courseId;
        this.from = from;
        this.to = to;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.status = status;
        this.sessionRegister = sessionRegister;
    }

    public void register(NsUser user) {
        status.validate();

        sessionRegister.register(user);
    }

}
