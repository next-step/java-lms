package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Enrollment {
    private Long sessionId;

    private Applies applies;

    private SessionState sessionState;

    private SessionProgressStatus sessionProgressStatus;

    private SessionRecruitStatus sessionRecruitStatus;

    public Enrollment(Long sessionId, Applies applies, SessionState sessionState,
                      SessionProgressStatus sessionProgressStatus, SessionRecruitStatus sessionRecruitStatus) {
        this.sessionId = sessionId;
        this.applies = applies;
        this.sessionState = sessionState;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionRecruitStatus = sessionRecruitStatus;
    }

    public Apply apply(Long nsUserId, Payment payment, LocalDateTime date) {
        checkPaymentIsPaid(nsUserId, payment);
        checkStatusOnRecruit();
        checkStatusOnReadyOrOnGoing();
        checkChargedAndApplySizeIsValid();
        checkApplicantAlreadyExisted(nsUserId);

        return new Apply(sessionId, nsUserId, false, date);
    }

    private void checkPaymentIsPaid(Long nsUserId, Payment payment) {
        if (this.charged()) {
            checkPaymentIsValid(nsUserId, payment);
        }
    }

    public boolean charged() {
        return this.sessionState.getSessionType().charged();
    }

    private void checkPaymentIsValid(Long nsUserId, Payment payment) {
        if (payment == null ||
                !payment.isPaid(
                        nsUserId,
                        sessionId,
                        sessionState.getAmount()
                )
        ) {
            throw new IllegalArgumentException("결제를 다시 확인하세요.");
        }
    }

    private void checkStatusOnRecruit() {
        if (this.notRecruiting()) {
            throw new IllegalArgumentException("강의 신청은 모집 중일 때만 가능 합니다.");
        }
    }

    private void checkStatusOnReadyOrOnGoing() {
        if (this.notReadyOrOnGoing()) {
            throw new IllegalArgumentException("강의 신청은 준비, 진행중일 때만 가능 합니다.");
        }
    }

    private void checkChargedAndApplySizeIsValid() {
        if(chargedAndFull()) {
            throw new IllegalArgumentException("수강 신청 인원이 초과 되었습니다.");
        }
    }

    private boolean chargedAndFull() {
        return this.charged() && applySizeFull();
    }

    private boolean applySizeFull() {
        return this.sessionState.getQuota() == applies.size();
    }

    private void checkApplicantAlreadyExisted(Long nsUserId) {
        if (this.containsUserId(nsUserId)) {
            throw new IllegalArgumentException("이미 수강 신청 이력이 있습니다.");
        }
    }

    public boolean containsUserId(Long nsUserId) {
        return this.applies.getApplies().stream()
                .anyMatch(apply -> apply.isSameWithUserId(nsUserId));
    }

    public int size() {
        return this.applies.size();
    }

    public boolean notRecruiting() {
        return this.sessionRecruitStatus.notRecruiting();
    }

    public boolean notReadyOrOnGoing() {
        return this.sessionProgressStatus.notReadyOrOnGoing();
    }
}
