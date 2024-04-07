package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {

    private static final int MAXIMUM_NUMBER = 10_000;

    public FreeSession(LocalDateTime startedAt, LocalDateTime endedAt) {
        super(MAXIMUM_NUMBER, startedAt, endedAt);
    }

    @Override
    public void registers(List<NsUser> users) {
        users.forEach(this::register);
    }

    @Override
    public void register(NsUser user) {
        validateRecruiting();
        students.add(user);
    }

    @Override
    public List<NsUser> totalStudents() {
        return this.students;
    }
}
