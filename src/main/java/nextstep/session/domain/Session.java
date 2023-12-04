package nextstep.session.domain;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private int numberOfMembers;

    private int numberOfMaximumMembers;

    private SessionType sessionType;

    private SessionStatus status;

    private Image coverImage;

    private LocalDateTime startAt;

    private LocalDateTime endAt;
}
