package nextstep.courses.domain.course.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.image.Images;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends BaseEntity {
    private Long id;

    private Images images;

    private Applicants applicants;

    private SessionDetail sessionDetail;

    public Session(Images images, Duration duration, SessionState sessionState,
                   Long creatorId, LocalDateTime date) {
        this(0L, images, duration, sessionState, new Applicants(),
                RecruitStatus.NOT_RECRUIT, SessionStatus.READY, creatorId, date, null);
    }

    public Session(Long id, Images images, Duration duration, SessionState sessionState,
                   Applicants applicants, RecruitStatus recruitStatus, SessionStatus sessionStatus,
                   Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, images, applicants, new SessionDetail(duration, sessionState, sessionStatus, recruitStatus),
                creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Images images, Applicants applicants, SessionDetail sessionDetail,
                   Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        if (images == null) {
            throw new IllegalArgumentException("이미지를 추가해야 합니다");
        }

        if (applicants == null) {
            throw new IllegalArgumentException("수강생을 추가해야 합니다.");
        }

        if (sessionDetail == null) {
            throw new IllegalArgumentException("강의 정보를 추가해야 합니다.");
        }

        this.id = id;
        this.images = images;
        this.applicants = applicants;
        this.sessionDetail = sessionDetail;
    }

    public Apply apply(NsUser loginUser, Payment payment, LocalDateTime date) {
        checkStatusOnRecruit();

        if (this.sessionDetail.charged()) {
            checkPaymentIsPaid(loginUser, payment);
        }

        this.applicants.addApplicant(loginUser, sessionDetail.getSessionState());
        return toApply(loginUser, date);
    }

    private Apply toApply(NsUser loginUser, LocalDateTime date) {
        return new Apply(this, loginUser, date);
    }

    private void checkStatusOnRecruit() {
        if (this.sessionDetail.notRecruiting()) {
            throw new IllegalArgumentException("강의 신청은 모집 중일 때만 가능 합니다.");
        }
    }

    private void checkPaymentIsPaid(NsUser loginUser, Payment payment) {
        if (payment == null || !payment.isPaid(loginUser, this)) {
            throw new IllegalArgumentException("결제를 진행해 주세요.");
        }
    }

    public void changeOnReady(LocalDate date) {
        checkStartDateIsSameOrBefore(date);
        this.sessionDetail.changeOnReady();
    }

    public void changeOnRecruit(LocalDate date) {
        checkStartDateIsSameOrBefore(date);
        this.sessionDetail.changeOnRecruit();
    }

    private void checkStartDateIsSameOrBefore(LocalDate date) {
        if (sessionDetail.startDateIsSameOrBefore(date)) {
            throw new IllegalArgumentException("강의 시작일 이전에 변경 가능합니다.");
        }
    }

    public void changeOnEnd(LocalDate date) {
        checkEndDateIsSameOrAfter(date);
        this.sessionDetail.changeOnEnd();
    }

    private void checkEndDateIsSameOrAfter(LocalDate date) {
        if (sessionDetail.endDateIsSameOrAfter(date)) {
            throw new IllegalArgumentException("강의 종료일 이후 변경 가능합니다.");
        }
    }

    public void setSessionState(SessionState updateSessionState) {
        this.sessionDetail.setSessionState(updateSessionState);
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
        return this.applicants.size();
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Applicants getApplicants() {
        return applicants;
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

    public SessionStatus getSessionStatus() {
        return sessionDetail.getSessionStatus();
    }

    public RecruitStatus getRecruitStatus() {
        return sessionDetail.getRecruitStatus();
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", images=" + images +
                ", applicants=" + applicants +
                ", sessionDetail=" + sessionDetail +
                '}';
    }
}
