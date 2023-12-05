package nextstep.qna.domain;

import nextstep.payments.domain.Payment;
import nextstep.qna.domain.session.SessionStatus;

import java.time.LocalDateTime;

import static nextstep.qna.domain.session.SessionStatus.*;

public class FreeSession implements Session { // 무료
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private CoverImage coverImage;
    private SessionStatus status;

    private FreeSession() {
    }

    public static FreeSession of(String startDateTime, String endDateTime, CoverImage coverImage, SessionStatus status) {
        FreeSession freeSession = new FreeSession();
        freeSession.startDateTime = LocalDateTime.parse(startDateTime);
        freeSession.endDateTime = LocalDateTime.parse(endDateTime);
        freeSession.coverImage = coverImage;
        freeSession.status = status;
        return freeSession;
    }

    @Override
    public void recruit() {
        this.status = RECRUITING;
    }

    @Override
    public void end() {
        this.status = END;
    }

    @Override
    public boolean isPossibleToRegister(Payment payment) {
        checkSessionStatus();
        return true;
    }

    private void checkSessionStatus() {
        if (this.status != RECRUITING) {
            throw new IllegalStateException("강의 상태가 모집중이 아닙니다.");
        }
    }
}
