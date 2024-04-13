package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Users;

public class PaidSession extends Session {
    public static final String OVER_MAX_ENROLLMENTS = "유료 강의는 강의 최대 수강 인원을 초과할 수 없습니다.";
    public static final String PAYMENT_IS_NOT_MATCHING = "유료 강의는 결제 금액과 수강료가 일치해야 합니다.";

    private int maxEnrollments;
    private Long fee;

    public PaidSession(Long sessionId, SessionPeriod sessionPeriod,
                       CoverImage coverImage, SessionStatusEnum sessionStatus,
                       Users users, int maxEnrollments, Long fee) {
        super(sessionId, sessionPeriod, coverImage, sessionStatus, users);
        this.maxEnrollments = maxEnrollments;
        this.fee = fee;
    }

    @Override
    public void enrollStudent(NsUser user) {
        assertAllConditions(user);

        this.users.add(user);
    }

    private void assertAllConditions(NsUser user) {
        if (!this.isSessionOpened()) {
            throw new IllegalArgumentException(SESSION_NOT_OPENED);
        }

        if (isEnrollmentFull()) {
            throw new IllegalArgumentException(OVER_MAX_ENROLLMENTS);
        }

        if (!isPaymentAmountMatching(user)) {
            throw new IllegalArgumentException(PAYMENT_IS_NOT_MATCHING);
        }

        this.users.add(user);
    }

    private boolean isEnrollmentFull() {
        return maxEnrollments == this.users.getNumberOfUsers();
    }

    private boolean isPaymentAmountMatching(NsUser user) {
        return user.hasPaidForSession(getSessionId(), fee);
    }
}
