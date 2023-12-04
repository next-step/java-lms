package nextstep.session.domain;

import nextstep.image.domain.Image;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private int numberOfMembers;

    private int numberOfMaximumMembers;

    private SessionType sessionType;

    private SessionStatus status;

    private Image coverImage;

    private StartAt startAt;

    private EndAt endAt;

    public Session(Long id, int numberOfMembers, int numberOfMaximumMembers, SessionType sessionType, SessionStatus status, Image coverImage, StartAt startAt, EndAt endAt) {
        this.id = id;
        this.numberOfMembers = numberOfMembers;
        this.numberOfMaximumMembers = numberOfMaximumMembers;
        this.sessionType = sessionType;
        this.status = status;
        this.coverImage = coverImage;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Session create(
            int numberOfMembers,
            int numberOfMaximumMembers,
            SessionType sessionType,
            Image coverImage,
            LocalDateTime startAt,
            LocalDateTime endAt
    ) {
        return new Session(
                null,
                numberOfMembers,
                numberOfMaximumMembers,
                sessionType,
                SessionStatus.PREPARING,
                coverImage,
                new StartAt(startAt),
                new EndAt(endAt)
        );
    }
}
