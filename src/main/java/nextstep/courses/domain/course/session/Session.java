package nextstep.courses.domain.course.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends BaseEntity {
    private Long id;

    private Image image;

    private Duration duration;

    private SessionState sessionState;

    private Applicants applicants;

    private SessionStatus session;

    public Session(Image image, Duration duration, SessionState sessionState,
                   Long creatorId, LocalDateTime date) {
        this(0L, image, duration, sessionState, new Applicants(),
                SessionStatus.READY, creatorId, date, null);
    }

    public Session(Long id, Image image, Duration duration, SessionState sessionState,
                   Applicants applicants, SessionStatus session, Long creatorId,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        if (image == null) {
            throw new IllegalArgumentException("이미지를 추가해야 합니다");
        }

        if (duration == null) {
            throw new IllegalArgumentException("기간을 추가해야 합니다.");
        }

        if (sessionState == null) {
            throw new IllegalArgumentException("강의 상태를 추가해야 합니다.");
        }

        this.id = id;
        this.image = image;
        this.duration = duration;
        this.sessionState = sessionState;
        this.applicants = applicants;
        this.session = session;
    }

    public boolean sameAmount(Long amount) {
        return this.sessionState.sameAmount(amount);
    }

    public boolean sameId(Long sessionId) {
        return Objects.equals(this.id, sessionId);
    }

    public Long getId() {
        return this.id;
    }

    public int applyCount() {
        return this.applicants.size();
    }

    public void apply(NsUser loginUser, Payment payment) {
        checkStatusOnRecruit();

        if (this.sessionState.charged()) {
            checkPaymentIsPaid(loginUser, payment);
        }

        this.applicants.addApplicant(loginUser, sessionState);
    }

    private void checkStatusOnRecruit() {
        if (this.session != SessionStatus.RECRUIT) {
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
        this.session = SessionStatus.READY;
    }

    public void changeOnRecruit(LocalDate date) {
        checkStartDateIsSameOrBefore(date);
        this.session = SessionStatus.RECRUIT;
    }

    private void checkStartDateIsSameOrBefore(LocalDate date) {
        if (duration.startDateIsSameOrBefore(date)) {
            throw new IllegalArgumentException("강의 시작일 이전에 변경 가능합니다.");
        }
    }

    public void changeOnEnd(LocalDate date) {
        checkEndDateIsSameOrAfter(date);
        this.session = SessionStatus.END;
    }

    private void checkEndDateIsSameOrAfter(LocalDate date) {
        if (duration.endDateIsSameOrAfter(date)) {
            throw new IllegalArgumentException("강의 종료일 이후 변경 가능합니다.");
        }
    }

    public Image getImage() {
        return image;
    }

    public Duration getDuration() {
        return duration;
    }

    public SessionState getSessionState() {
        return this.sessionState;
    }

    public Applicants getApplicants() {
        return applicants;
    }

    public SessionStatus getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", image=" + image +
                ", duration=" + duration +
                ", sessionState=" + sessionState +
                ", applicants=" + applicants +
                ", session=" + session +
                '}';
    }
}
