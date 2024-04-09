package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Session {

    protected final long id;
    protected final int year;
    protected final int maximumNumberOfStudent;
    protected final List<NsUser> students = new ArrayList<>();
    protected final LocalDateTime startedAt;
    protected final LocalDateTime endedAt;
    protected SessionStatus status = SessionStatus.PREPARING;

    protected Session(int id, int year, int maximumNumberOfStudent, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.year = year;
        this.maximumNumberOfStudent = maximumNumberOfStudent;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public abstract void registers(List<NsUser> users);

    public abstract void register(NsUser user);

    public void changeStatus(SessionStatus status) {
        this.status = status;
    }

    public List<NsUser> totalStudents() {
        return new ArrayList<>(this.students);
    }

    protected void validateRecruiting() {
        if (!SessionStatus.isRecruiting(status)) {
            throw new IllegalArgumentException("현재 모집 중인 강의가 아닙니다.");
        }
    }

    public long getId() {
        return this.id;
    }
}
