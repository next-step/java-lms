package nextstep.courses.domain.course.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.courses.domain.course.session.image.Images;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends BaseEntity {
    private Long id;

    private Images images;

    private SessionDetail sessionDetail;

    public Session(Images images, Duration duration, SessionState sessionState,
                   Long creatorId, LocalDateTime date) {
        this(0L, images, duration, sessionState, SessionRecruitStatus.NOT_RECRUIT,
                SessionProgressStatus.READY, creatorId, date, null);
    }

    public Session(Long id, Images images, Duration duration, SessionState sessionState,
                   SessionRecruitStatus sessionRecruitStatus, SessionProgressStatus sessionProgressStatus,
                   Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, images, new SessionDetail(duration, sessionState, sessionProgressStatus, sessionRecruitStatus),
                creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Images images, SessionDetail sessionDetail,
                   Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        if (images == null) {
            throw new IllegalArgumentException("이미지를 추가해야 합니다");
        }

        if (sessionDetail == null) {
            throw new IllegalArgumentException("강의 정보를 추가해야 합니다.");
        }

        this.id = id;
        this.images = images;
        this.sessionDetail = sessionDetail;
    }

    public Apply apply(NsUser loginUser, Payment payment, LocalDateTime date) {
        checkPaymentIsPaid(loginUser, payment);

        return this.sessionDetail.addApply(this.id, loginUser.getId(), date);
    }

    private void checkPaymentIsPaid(NsUser loginUser, Payment payment) {
        if (sessionDetail.charged()) {
            checkPaymentIsValid(loginUser, payment);
        }
    }

    private void checkPaymentIsValid(NsUser loginUser, Payment payment) {
        if (payment == null || !payment.isPaid(loginUser, this)) {
            throw new IllegalArgumentException("결제를 다시 확인하세요.");
        }
    }

    public Apply approve(NsUser loginUser, Apply apply, LocalDateTime date) {
        checkUserHasAuthor(loginUser);

        return sessionDetail.approve(apply, date);
    }

    public Apply cancel(NsUser loginUser, Apply apply, LocalDateTime date) {
        checkUserHasAuthor(loginUser);

        return sessionDetail.cancel(apply, date);
    }

    private void checkUserHasAuthor(NsUser loginUser) {
        if(!loginUser.hasAuthor()) {
            throw new IllegalArgumentException("신청을 승인 할 권한이 없습니다.");
        }
    }

    public void changeOnReady(LocalDate date) {
        this.sessionDetail.changeOnReady(date);
    }

    public void changeOnGoing(LocalDate date) {
        this.sessionDetail.changeOnGoing(date);
    }

    public void changeOnEnd(LocalDate date) {
        this.sessionDetail.changeOnEnd(date);
    }

    public void changeSessionState(SessionState updateSessionState) {
        this.sessionDetail.changeSessionState(updateSessionState);
    }

    public boolean sameAmount(Long amount) {
        return this.sessionDetail.sameAmount(amount);
    }

    public boolean sameId(Long sessionId) {
        return Objects.equals(this.id, sessionId);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int applyCount() {
        return this.sessionDetail.size();
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public SessionDetail getSessionDetail() {
        return sessionDetail;
    }

    public Duration getDuration() {
        return sessionDetail.getDuration();
    }

    public SessionState getSessionState() {
        return sessionDetail.getSessionState();
    }

    public SessionProgressStatus getSessionStatus() {
        return sessionDetail.getSessionStatus();
    }

    public SessionRecruitStatus getRecruitStatus() {
        return sessionDetail.getRecruitStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", images=" + images +
                ", sessionDetail=" + sessionDetail +
                '}';
    }
}
