package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    int limitOfApplicants;

    public PaidSession(
            LocalDateTime startDate,
            LocalDateTime endDate,
            CoverImage coverImage,
            Long price,
            SessionStatus status,
            int limitOfApplicants,
            int numberOfApplicants
    ) {
        super(startDate, endDate, coverImage, price, status, numberOfApplicants);
        this.limitOfApplicants = limitOfApplicants;
    }

    public PaidSession(Long price, SessionStatus status) {
        super(price, status);
    }

    @Override
    protected void register() {
        if (status != SessionStatus.ACCEPTING) {
            throw new CannotRegisterSessionException("강의가 등록할 수 있는 상태가 아닙니다.");
        }

        if (limitOfApplicants == numberOfApplicants) {
            throw new CannotRegisterSessionException("강의 수강 가능 인원을 초과하였습니다.");
        }

        numberOfApplicants += 1;
    }
}
