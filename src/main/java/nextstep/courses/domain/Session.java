package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Session {

    protected final int maximumNumberOfStudent;
    protected final List<NsUser> students = new ArrayList<>();
    protected final LocalDateTime startedAt;
    protected final LocalDateTime endedAt;
    protected SessionStatus status = SessionStatus.PREPARING;

    protected Session(int maximumNumberOfStudent, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.maximumNumberOfStudent = maximumNumberOfStudent;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public abstract void registers(List<NsUser> users);

    public abstract void register(NsUser user);

    public void changeStatus(SessionStatus status) {
        this.status = status;
    }

    public abstract List<NsUser> totalStudents();

    protected void validateRecruiting() {
        if (!SessionStatus.isRecruiting(status)) {
            throw new IllegalArgumentException("현재 모집 중인 강의가 아닙니다.");
        }
    }
}
