package nextstep.qna.domain;

import nextstep.payments.domain.Payment;
import nextstep.qna.domain.session.SessionPeriod;
import nextstep.qna.domain.session.SessionStatus;

import static nextstep.qna.domain.session.SessionStatus.END;
import static nextstep.qna.domain.session.SessionStatus.RECRUITING;

public class PaidSession implements Session { // 유료
    private SessionPeriod sessionPeriod;
    private CoverImage coverImage;
    private SessionStatus status;
    private Long maxNumberOfAttendees;
    private Long currentNumberOfAttendees;
    private Long price;

    private PaidSession() {
    }

    public static PaidSession of(String startDateTime, String endDateTime, CoverImage coverImage, SessionStatus status, long maxNumberOfAttendees, long currentNumberOfAttendees, long price) {
        PaidSession paidSession = new PaidSession();
        paidSession.sessionPeriod = SessionPeriod.of(startDateTime, endDateTime);
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
    public boolean isPossibleToRegister(Payment payment) {
        checkSessionStatus();
        checkPrice(payment.amounts());
        checkMaxNumberOfAttendees();
        return true;
    }

    private void checkMaxNumberOfAttendees() {
        if (this.maxNumberOfAttendees <= this.currentNumberOfAttendees) { // TODO 후에 트랜젝션 처리 시 주의
            throw new IllegalStateException("수강인원이 초과 되었습니다.");
        }
    }

    private void checkPrice(Long paidAmount) {
        if (!this.price.equals(paidAmount)) {
            throw new IllegalArgumentException("강의 가격과 다릅니다.");
        }
    }

    private void checkSessionStatus() {
        if (this.status != RECRUITING) {
            throw new IllegalStateException("강의 상태가 모집중이 아닙니다.");
        }
    }
}
