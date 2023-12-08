package nextstep.tutor.domain;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionUserStatus;
import nextstep.tutor.exception.SessionApproveException;
import nextstep.tutor.exception.SessionCancelException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class NsTutor {

    private Long id;

    private String tutorId;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsTutor() {
    }

    public NsTutor(Long id, String tutorId, String password, String name, String email) {
        this(id, tutorId, password, name, email, LocalDateTime.now(), null);
    }

    public NsTutor(Long id, String tutorId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tutorId = tutorId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SessionUserStatus approve(Session session, NsUser nsUser) {
        if (!session.isTutor(this.id)) {
            throw new SessionApproveException("본인 담당 강의만 수강 승인 할 수 있습니다.");
        }

        if (!nsUser.isSelected()) {
            throw new SessionApproveException("선발 되지 않은 유저는 수강 승인 할 수 없습니다.");
        }
        return SessionUserStatus.APPROVE;
    }

    public SessionUserStatus cancel(Session session) {
        if (!session.isTutor(this.id)) {
            throw new SessionCancelException("본인 담당 강의만 수강 취소 할 수 있습니다.");
        }
        return SessionUserStatus.CANCEL;
    }

}
