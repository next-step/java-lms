package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.image.Images;
import nextstep.courses.domain.session.type.SessionType;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.courses.domain.session.user.SessionUser;
import nextstep.courses.domain.session.user.SessionUsers;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public abstract class Session {
    protected final Long id;
    protected final String title;
    private final Course course;
    private final Period period;
    private final Images images;
    private final SessionStatus status;
    private final SessionType type;
    protected final SessionUsers sessionUsers;
    private Long creatorId;

    public Session(Course course, String title, Period period, List<Image> images, SessionUsers sessionUsers, SessionType type) {
        this(0L, title, course, period, images, SessionStatus.READY, sessionUsers, type, 0L);
    }

    public Session(Long id, String title, Course course, Period period, List<Image> images, SessionStatus status, SessionUsers sessionUsers, SessionType type, Long creatorId) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.period = period;
        this.images = Images.from(images);
        this.status = status;
        this.sessionUsers = sessionUsers;
        this.type = type;
        this.creatorId = creatorId;
    }

    public Long getId() {
        return this.id;
    }

    public void enroll(NsUser nsUser, LocalDate date) {
        if (!canEnrollStatus()) {
            throw new IllegalArgumentException("강의는 현재 모집하고 있지 않습니다.");
        }
        if (period.isEnd(date)) {
            throw new IllegalArgumentException("강의가 종료되어 수강신청을 할 수 없습니다.");
        }
        assertCanEnroll();
        sessionUsers.add(this.id, nsUser.getId());
    }

    public abstract void assertCanEnroll();

    private boolean canEnrollStatus() {
        return this.status == SessionStatus.RECRUITING;
    }

    private boolean equalsType(SessionType type) {
        if (type == null) {
            return false;
        }
        return this.type == type;
    }

    public boolean isFreeSession() {
        return equalsType(SessionType.FREE);
    }

    public Payment toPayment(NsUser nsUser) {
        if (isFreeSession()) {
            throw new IllegalArgumentException("무료강의는 결제를 할 수 없습니다.");
        }
        PaidSession paidSession = (PaidSession) this;
        return paidSession.toPayment(nsUser);
    }

    public void addSessionUser(List<NsUser> nsUsers) {
        this.sessionUsers.addAll(nsUsers, this.getId());
    }

    public void addImages(List<Image> images) {
        this.images.addAll(images);
    }

    public void accessUser(SessionUser accessUserTarget, Long creatorId) {
        if (creatorId != this.getCreatorId()) {
            throw new IllegalArgumentException("자신의 강의만 유저 승인을 할 수 있습니다.");
        }
        this.sessionUsers.accessSession(accessUserTarget);
    }

    public Long getCreatorId() {
        return this.creatorId;
    }
}
