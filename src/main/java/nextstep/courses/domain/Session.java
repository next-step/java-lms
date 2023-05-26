package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private int maxUserCount;
    private Long id;
    private final List<NsUser> users = new ArrayList<>();
    private SessionBillingType sessionBillingType;
    private SessionStatusType sessionStatusType;
    private SessionCoverImage sessionCoverImage;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;

    public Session(int maxUserCount, Long id, SessionBillingType sessionBillingType, SessionStatusType sessionStatusType, SessionCoverImage sessionCoverImage, SessionPeriod sessionPeriod) {
        if (sessionBillingType == null) {
            throw new IllegalArgumentException("과금 유형을 선택해주세요");
        }

        if (sessionPeriod == null) {
            throw new IllegalArgumentException("강의 기간을 설정해주세요");
        }

        this.maxUserCount = maxUserCount;
        this.id = id;
        this.sessionBillingType = sessionBillingType;
        this.sessionStatusType = sessionStatusType;
        if ( sessionStatusType == null )
        {
            this.sessionStatusType = SessionStatusType.OPEN;
        }
        this.sessionCoverImage = sessionCoverImage;
        this.sessionPeriod = sessionPeriod;
    }

    public void register(NsUser user) {
        if (!this.sessionStatusType.isOpen()) {
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

    public SessionBillingType getSessionType() {
        return sessionBillingType;
    }

    public SessionStatusType getSessionStatus() {
        return sessionStatusType;
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
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
