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

    private SessionApprovalStatus status;

    public SessionStudent(NsUser user) {
        this.user = user;
        this.registrationAt = LocalDateTime.now();
        this.status = SessionApprovalStatus.WAITING;
    }

    public SessionStudent(Long id, NsUser user, LocalDateTime registrationAt, SessionApprovalStatus status) {
        this.id = id;
        this.user = user;
        this.registrationAt = registrationAt;
        this.status = status;
    }

    public NsUser getUser() {
        return user;
    }

    public LocalDateTime getRegistrationAt() {
        return registrationAt;
    }

    public SessionApprovalStatus getStatus() {
        return status;
    }

    public boolean isApproval() {
        return this.status == SessionApprovalStatus.APPROVAL;
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

    public void approval() {
        this.status = SessionApprovalStatus.APPROVAL;
    }

    public void cancel() {
        this.status = SessionApprovalStatus.CANCEL;
    }
}
