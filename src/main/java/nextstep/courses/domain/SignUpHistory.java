package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;

public class SignUpHistory {

    private Long id;

    private Session session;

    private NextStepUser user;

    private LocalDateTime createAt;

    public SignUpHistory(Session session, NextStepUser user) {
        this(0L, session, user, LocalDateTime.now());
    }

    public SignUpHistory(Long id, Session session, NextStepUser user, LocalDateTime createAt) {
        this.id = id;
        this.session = session;
        this.user = user;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public NextStepUser getUser() {
        return user;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void saveSession(Session session) {
        this.session = session;
    }

    public void saveUser(NextStepUser user) {
        this.user = user;
    }

}
