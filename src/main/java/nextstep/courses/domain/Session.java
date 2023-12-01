package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.SessionStateException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.util.HashSet;

public abstract class Session {
    protected Long sessionId;
    protected CoverImage coverImage;
    protected ProgressPeriod progressPeriod;
    protected SessionState state;
    protected Participants participants;


    protected Session(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate, SessionState state) {
        this.coverImage = coverImage;
        this.progressPeriod = new ProgressPeriod(startDate, endDate);
        this.state = state;
        this.participants = new Participants(new HashSet<>());
    }


    public abstract void apply(Payment payment);

    protected void validateState() {
        if (state.isNotRecruiting()) {
            throw new SessionStateException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다");
        }
    }
}
