package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FreeSession implements Session {

    private String title;
    private SessionPeriod period;
    private CoverImage coverImage;
    private SessionStatus sessionStatus;
    private final List<NsUser> enrolledUsers;

    public FreeSession(String title, SessionPeriod period, CoverImage coverImage) {
        this.title = title;
        this.period = period;
        this.coverImage = coverImage;
        this.sessionStatus = SessionStatus.PREPARE;
        this.enrolledUsers = new ArrayList<>();
    }

    @Override
    public boolean isPaid() {
        return false;
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        if (!sessionStatus.equals(SessionStatus.OPEN)) {
            throw new IllegalStateException("모집중인 상태에서만 신청 가능합니다.");
        }
        enrolledUsers.add(nsUser);
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

    public List<NsUser> getEnrolledUsers() {
        return Collections.unmodifiableList(enrolledUsers);
    }
}