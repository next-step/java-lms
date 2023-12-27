package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.courses.domain.course.session.apply.ApprovalStatus;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Enrollment {
    private final Long sessionId;

    private final Applies applies;

    private final SessionDetail sessionDetail;

    public Enrollment(Long sessionId, Applies applies, SessionDetail sessionDetail) {
        this.sessionId = sessionId;
        this.applies = applies;
        this.sessionDetail = sessionDetail;
    }

    public Apply apply(Long nsUserId, Payment payment, LocalDateTime date) {
        checkPaymentIsPaid(nsUserId, payment);
        checkStatusOnRecruit();
        checkStatusOnReadyOrOnGoing();
        checkChargedAndApplySizeIsValid();
        checkApplicantAlreadyExisted(nsUserId);

        return new Apply(sessionId, nsUserId, ApprovalStatus.WAIT, date);
    }

    private void checkPaymentIsPaid(Long nsUserId, Payment payment) {
        if (this.sessionDetail.charged()) {
            checkPaymentIsValid(nsUserId, payment);
        }
    }

    private void checkPaymentIsValid(Long nsUserId, Payment payment) {
        if (payment == null ||
                !payment.isPaid(
                        nsUserId,
                        sessionId,
                        this.sessionDetail.getAmount()
                )
        ) {
            throw new IllegalArgumentException("결제를 다시 확인하세요.");
        }
    }

    private void checkStatusOnRecruit() {
        if (this.sessionDetail.notRecruiting()) {
            throw new IllegalArgumentException("강의 신청은 모집 중일 때만 가능 합니다.");
        }
    }

    private void checkStatusOnReadyOrOnGoing() {
        if (this.sessionDetail.notReadyOrOnGoing()) {
            throw new IllegalArgumentException("강의 신청은 준비, 진행중일 때만 가능 합니다.");
        }
    }

    private void checkChargedAndApplySizeIsValid() {
        if(this.sessionDetail.chargedAndFull(applies.size())) {
            throw new IllegalArgumentException("수강 신청 인원이 초과 되었습니다.");
        }
    }

    private void checkApplicantAlreadyExisted(Long nsUserId) {
        if (this.applies.containsUserId(nsUserId)) {
            throw new IllegalArgumentException("이미 수강 신청 이력이 있습니다.");
        }
    }
}
