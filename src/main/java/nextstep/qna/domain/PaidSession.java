package nextstep.qna.domain;

import nextstep.qna.domain.session.SessionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static nextstep.qna.domain.session.SessionStatus.END;
import static nextstep.qna.domain.session.SessionStatus.RECRUITING;

public class PaidSession implements Session { // 유료
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private CoverImage coverImage;
    private SessionStatus status;
    private long maxNumberOfAttendees;
    private long currentNumberOfAttendees;
    private long price;

    private PaidSession() {
    }

    public static PaidSession of(String startDateTime, String endDateTime, CoverImage coverImage, SessionStatus status, long maxNumberOfAttendees, long currentNumberOfAttendees, long price) {
        PaidSession paidSession = new PaidSession();
        paidSession.startDateTime = LocalDateTime.parse(startDateTime);
        paidSession.endDateTime = LocalDateTime.parse(endDateTime);
        paidSession.coverImage = coverImage;
        paidSession.status = status;
        paidSession.maxNumberOfAttendees = maxNumberOfAttendees;
        paidSession.currentNumberOfAttendees = currentNumberOfAttendees;
        paidSession.price = price;
        return paidSession;
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
    public boolean checkForRegister(long paidAmount) {
        checkSessionStatus();
        checkPrice(paidAmount);
        checkMaxNumberOfAttendees();
        return true;
    }

    private void checkMaxNumberOfAttendees() {
        if(this.maxNumberOfAttendees <= this.currentNumberOfAttendees) { // TODO 후에 트랜젝션 처리 시 주의
            throw new IllegalStateException("수강인원이 초과 되었습니다.");
        }
    }

    private void checkPrice(long paidAmount) {
        if(this.price != paidAmount) {
            throw new IllegalArgumentException("강의 가격과 다릅니다.");
        }
    }

    private void checkSessionStatus() {
        if (this.status != RECRUITING) {
            throw new IllegalStateException("강의 상태가 모집중이 아닙니다.");
        }
    }
}
