package nextstep.courses.domain.builder;

import nextstep.courses.domain.Period;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRegistrationStatus;

import java.time.LocalDateTime;

public class SessionBuilder {
    private Long id;
    private Period period;
    private String imageUrl;
    private boolean isFree;
    private SessionRegistrationStatus sessionRegistrationStatus;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SessionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder period(Period period) {
        this.period = period;
        return this;
    }

    public SessionBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public SessionBuilder isFree(boolean isFree) {
        this.isFree = isFree;
        return this;
    }

    public SessionBuilder sessionRegistrationStatus(SessionRegistrationStatus sessionRegistrationStatus) {
        this.sessionRegistrationStatus = sessionRegistrationStatus;
        return this;
    }

    public SessionBuilder creatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public SessionBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SessionBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Session build() {
        Session session = new Session(id, period, imageUrl, isFree, sessionRegistrationStatus, creatorId, createdAt, updatedAt);
        return session;
    }
}
