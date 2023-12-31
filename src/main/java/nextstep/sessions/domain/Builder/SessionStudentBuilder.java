package nextstep.sessions.domain.Builder;

import nextstep.sessions.domain.SessionApprovalStatus;
import nextstep.sessions.domain.SessionStudent;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.builder.NsUserBuilder;

import java.time.LocalDateTime;

public class SessionStudentBuilder {
    private Long id = 1L;
    private NsUser user = new NsUserBuilder().build();
    private LocalDateTime registrationAt = LocalDateTime.now();
    private SessionApprovalStatus status = SessionApprovalStatus.WAITING;
    private Long sessionId = 1L;

    public SessionStudentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionStudentBuilder withUser(NsUser user) {
        this.user = user;
        return this;
    }

    public SessionStudentBuilder withRegistrationAt(LocalDateTime registrationAt) {
        this.registrationAt = registrationAt;
        return this;
    }

    public SessionStudentBuilder withSessionApprovalStatus(SessionApprovalStatus status) {
        this.status = status;
        return this;
    }

    public SessionStudentBuilder withSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public SessionStudent build() {
        return new SessionStudent(id, user, registrationAt, status, sessionId);
    }
}
