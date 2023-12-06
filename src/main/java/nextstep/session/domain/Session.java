package nextstep.session.domain;

import nextstep.image.domain.Image;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Session {

    private Long id;

    private Set<NsUser> members = new HashSet<>();

    private int numberOfMaximumMembers;

    private SessionType sessionType;

    private SessionStatus status;

    private Image coverImage;

    private StartAt startAt;

    private EndAt endAt;

    public Session(Long id, Set<NsUser> members, int numberOfMaximumMembers, SessionType sessionType, SessionStatus status, Image coverImage, StartAt startAt, EndAt endAt) {
        this.id = id;
        this.members = members;
        this.numberOfMaximumMembers = numberOfMaximumMembers;
        this.sessionType = sessionType;
        this.status = status;
        this.coverImage = coverImage;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Session create(
            int numberOfMaximumMembers,
            SessionType sessionType,
            Image coverImage,
            LocalDateTime startAt,
            LocalDateTime endAt
    ) {
        return new Session(
                null,
                new HashSet<>(),
                numberOfMaximumMembers,
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

        if (sessionType == SessionType.FREE) {
            return;
        }

        if (numberOfMaximumMembers >= members.size()) {
            throw new IllegalStateException("최대 수강 인원을 초과하였습니다.");
        }
    }

    public void register() {
        isRegistrable();
    }
}
