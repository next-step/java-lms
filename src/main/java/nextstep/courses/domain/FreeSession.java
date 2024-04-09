package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {

    private static final int MAXIMUM_NUMBER = Integer.MAX_VALUE;

    public FreeSession(long id, int year, LocalDateTime startedAt, LocalDateTime endedAt) {
        super(id, year, MAXIMUM_NUMBER, startedAt, endedAt);
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
}
