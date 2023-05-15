package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public final class SessionBuilder {
    private Long id;
    private List<NsUser> users;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
    private String coverImageUrl;
    private int maxUserCount;
    private SessionPeriod sessionPeriod;

    private SessionBuilder() {
    }

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withUsers(List<NsUser> users) {
        this.users = users;
        return this;
    }

    public SessionBuilder withSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
        return this;
    }

    public SessionBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public SessionBuilder withCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
        return this;
    }

    public SessionBuilder withMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
        return this;
    }

    public SessionBuilder withSessionPeriod(SessionPeriod sessionPeriod) {
        this.sessionPeriod = sessionPeriod;
        return this;
    }

    public Session build() {
        return new Session(id, users, sessionType, sessionStatus, coverImageUrl, maxUserCount, sessionPeriod);
    }
}
