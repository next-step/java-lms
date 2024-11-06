package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;

import java.time.LocalDateTime;

public class FreeSession extends Session {
    public FreeSession(
            LocalDateTime startDate, LocalDateTime endDate, CoverImage coverImage, SessionStatus status, int numberOfApplicants
    ) {
        super(startDate, endDate, coverImage, 0L, status, numberOfApplicants);
    }

    public FreeSession(SessionStatus status) {
        super(0L, status);
    }

    protected void register() {
        if (status != SessionStatus.ACCEPTING) {
            throw new CannotRegisterSessionException("강의가 등록할 수 있는 상태가 아닙니다.");
        }

        numberOfApplicants += 1;
    }
}
