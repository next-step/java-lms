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
        validateStatus();
        students.add(user);
    }

    private void validateStatus() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 신청 가능합니다.");
        }
    }
}
