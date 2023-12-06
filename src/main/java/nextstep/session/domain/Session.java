package nextstep.session.domain;

import nextstep.image.domain.Image;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private Users members;

    private SessionType sessionType;

    private SessionStatus status;

    private Image coverImage;

    private StartAt startAt;

    private EndAt endAt;

    public Session(Long id, Users members, SessionType sessionType, SessionStatus status, Image coverImage, StartAt startAt, EndAt endAt) {
        this.id = id;
        this.members = members;
        this.sessionType = sessionType;
        this.status = status;
        this.coverImage = coverImage;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Session create(
            Users members,
            SessionType sessionType,
            Image coverImage,
            LocalDateTime startAt,
            LocalDateTime endAt
    ) {
        return new Session(
                null,
                members,
                sessionType,
                SessionStatus.PREPARING,
                coverImage,
                new StartAt(startAt),
                new EndAt(endAt)
        );
    }

    public void register(NsUser user) {
        if (!status.isRegistrable()) {
            throw new IllegalStateException("수강 신청 불가능한 상태입니다.");
        }
        members.register(user, sessionType);
    }
}
