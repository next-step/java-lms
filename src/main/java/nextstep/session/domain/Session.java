package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Session {
    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;

    private Long id;

    private Long creatorId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDate startDate;

    private LocalDate endDate;

    private SessionImage sessionImage;

    protected SessionStatus sessionStatus = DEFAULT_SESSION_STATUS;
    protected SessionStudents students = new SessionStudents();

    public Session(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = sessionImage;
    }

    public void enroll(NsUser user) {
        validateStatus();
        validateCommonEnroll(user);
        students.add(user);
    }

    private void validateStatus() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 신청 가능합니다.");
        }
    }

    protected void validateCommonEnroll(NsUser nsUser) {
    }

    public List<NsUser> getStudents() {
        return students.getStudents();
    }

    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
    }
}
