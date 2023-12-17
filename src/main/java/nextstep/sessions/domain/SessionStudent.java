package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionStudent {
    /*
    강의 결제한 학생
    학생 아이디와 신청일을 관리한다.
     */

    private Long id;

    private NsUser user;

    private LocalDateTime registrationAt;

    public SessionStudent(NsUser user) {
        this.user = user;
        this.registrationAt = LocalDateTime.now();
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
