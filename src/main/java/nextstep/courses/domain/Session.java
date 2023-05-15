package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
    private final List<NsUser> users = new ArrayList<>();

    private SessionBillType sessionBillType;

    private SessionStatus sessionStatus;

    private SessionCoverImage sessionCoverImage;

    private int maxUserCount;

    private SessionPeriod sessionPeriod;
    public Session() {
    }

    public Session(Long id, SessionBillType sessionBillType, SessionStatus sessionStatus, SessionCoverImage sessionCoverImage, int maxUserCount, SessionPeriod sessionPeriod) {
        if (sessionBillType == null) {
            throw new IllegalArgumentException("과금 유형을 선택해주세요");
        }

        if (sessionPeriod == null) {
            throw new IllegalArgumentException("강의 기간을 설정해주세요");
        }

        this.id = id;
        this.sessionBillType = sessionBillType;
        this.sessionStatus = sessionStatus == null ? SessionStatus.OPEN : sessionStatus;
        this.sessionCoverImage = sessionCoverImage;
        this.maxUserCount = maxUserCount;
        this.sessionPeriod = sessionPeriod;
    }

    public void register(NsUser user) {
        if (!this.sessionStatus.isOpen()) {
            throw new IllegalArgumentException("수강신청은 모집중일때만 등록이 가능합니다.");
        }

        if (maxUserCount <= users.size()) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }

        users.add(user);
    }

    public Long getId() {
        return id;
    }

    public List<NsUser> getUsers() {
        return users;
    }

    public SessionBillType getSessionType() {
        return sessionBillType;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionCoverImage getCoverImageUrl() {
        return sessionCoverImage;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public void addUser(NsUser nsUser) {
        users.add(nsUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
