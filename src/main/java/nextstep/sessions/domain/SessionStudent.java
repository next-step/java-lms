package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionStudent {

    private Long id;

    private NsUser user;

    // 신청일
    private LocalDateTime registrationAt;

    public SessionStudent(NsUser user, LocalDateTime registrationAt) {
        this.user = user;
        this.registrationAt = registrationAt;
    }

    public SessionStudent(Long id, NsUser user, LocalDateTime registrationAt) {
        this.id = id;
        this.user = user;
        this.registrationAt = registrationAt;
    }

    public NsUser getUser() {
        return user;
    }

    public LocalDateTime getRegistrationAt() {
        return registrationAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SessionStudent that = (SessionStudent) obj;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
