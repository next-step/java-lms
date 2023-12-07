package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class FreeSession extends Session {
    public FreeSession(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        super(generation, creatorId, startDate, endDate, sessionImage);
    }

    public static FreeSession create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        return new FreeSession(generation, creatorId, startDate, endDate, sessionImage);
    }

    @Override
    public void enroll(NsUser user) {
        validateCommonEnroll();
        students.add(user);
    }
}
