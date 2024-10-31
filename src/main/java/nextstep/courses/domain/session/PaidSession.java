package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaidSession implements Session {

    private final String title;
    private final SessionPeriod period;
    private final CoverImage coverImage;
    private SessionStatus sessionStatus;
    private final Long fee;
    private final int maxEnrollments;
    private final List<NsUser> enrolledUsers;

    public PaidSession(String title, SessionPeriod period, CoverImage coverImage, Long fee, int maxEnrollments) {
        this.title = title;
        this.period = period;
        this.coverImage = coverImage;
        this.fee = fee;
        this.maxEnrollments = maxEnrollments;
        this.sessionStatus = SessionStatus.PREPARE;
        this.enrolledUsers = new ArrayList<>();
    }

    @Override
    public boolean isPaid() {
        return true;
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        if (isEnrollmentNotOpen()) {
            throw new IllegalStateException("모집중인 상태에서만 신청 가능합니다.");
        }
        if (isPaymentMismatched(payment)) {
            throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
        }
        if (isEnrollmentFull()) {
            throw new IllegalStateException("수강 인원이 초과되었습니다.");
        }
        enrolledUsers.add(nsUser);
    }

    private boolean isEnrollmentNotOpen() {
        return sessionStatus != SessionStatus.OPEN;
    }

    private boolean isEnrollmentFull() {
        return enrolledUsers.size() >= maxEnrollments;
    }

    private boolean isPaymentMismatched(Payment payment) {
        return !fee.equals(payment.getAmount());
    }

    @Override
    public void openEnrollment() {
        if (sessionStatus == SessionStatus.PREPARE) {
            sessionStatus = SessionStatus.OPEN;
        }
    }

    public String getTitle() {
        return title;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public Long getFee() {
        return fee;
    }

    public List<NsUser> getEnrolledUsers() {
        return Collections.unmodifiableList(enrolledUsers);
    }
}