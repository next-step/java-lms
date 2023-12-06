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

    private void isRegistrable() {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("수강신청은 모집중인 상태일 때만 가능합니다.");
        }
    }

    public void register(NsUser user) {
        isRegistrable();
        members.register(user, sessionType);
    }
}
