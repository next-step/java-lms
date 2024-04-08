package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.exception.InvalidPaymentException;
import nextstep.payments.exception.PaymentAmountMismatchException;
import nextstep.sessions.exception.DuplicateEnrollmentException;
import nextstep.sessions.exception.DuplicateJoinException;
import nextstep.sessions.exception.InvalidSessionException;
import nextstep.sessions.exception.InvalidSessionJoinException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Session extends BaseEntity {
    private Long id;

    private Long courseId;

    private String title;

    private SessionState state;

    private RecruitmentState recruitment;

    private SessionType sessionType;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Set<NsUser> listener;

    private CoverImages coverImages;

    private Selection selection;

    private Set<Enrollment> enrollments;

    public Session(Long id, Long courseId, String title, SessionState state, RecruitmentState recruitment, SessionType sessionType, CoverImages coverImages, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updatedAt, Set<NsUser> listener, Selection selection, Set<Enrollment> enrollments) {
        validateCourseId(courseId);
        validateSessionType(sessionType);
        validatePeriod(startDate, endDate);

        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.state = state == null ? SessionState.PREPARING : state;
        this.recruitment = recruitment == null ? RecruitmentState.NOT_RECRUITING : recruitment;
        this.sessionType = sessionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.listener = listener == null ? new HashSet<>() : listener;
        this.coverImages = coverImages == null ? new CoverImages() : coverImages;
        this.selection = selection == null ? Selection.MANUAL : selection;
        this.enrollments = enrollments == null ? new HashSet<>() : enrollments;
    }

    private void validateCourseId(Long courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("코스 정보가 비어있습니다");
        }
    }

    private void validateSessionType(SessionType sessionType) {
        if (sessionType == null) {
            throw new IllegalArgumentException("무료/유료 강의 정보가 비어있습니다");
        }
    }

    private void validatePeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new InvalidSessionException("시작일이 종료일보다 이전이어야 합니다");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Long courseId;
        private String title;
        private SessionState state;
        private RecruitmentState recruitment;
        private SessionType sessionType;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Set<NsUser> listener;
        private CoverImages coverImages;
        private Selection selection;
        private Set<Enrollment> enrollments;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder courseId(Long courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder state(SessionState state) {
            this.state = state;
            return this;
        }

        public Builder recruitment(RecruitmentState recruitment) {
            this.recruitment = recruitment;
            return this;
        }

        public Builder sessionType(SessionType sessionType) {
            this.sessionType = sessionType;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder listener(Set<NsUser> listener) {
            this.listener = listener;
            return this;
        }

        public Builder coverImages(CoverImages coverImages) {
            this.coverImages = coverImages;
            return this;
        }

        public Builder selection(Selection selection) {
            this.selection = selection;
            return this;
        }

        public Builder enrollments(Set<Enrollment> enrollments) {
            this.enrollments = enrollments;
            return this;
        }

        public Session build() {
            return new Session(id, courseId, title, state, recruitment, sessionType, coverImages, startDate, endDate, createdAt, updatedAt, listener, selection, enrollments);
        }
    }

    public Enrollment requestJoin(NsUser loginUser, LocalDateTime now) {
        verifySession(loginUser, now);
        return new Enrollment(this.id, loginUser.getId(), this.selection.getDefaultEnrollmentState(), now);
    }

    private void verifySession(NsUser loginUser, LocalDateTime now) {
        if (this.listener.contains(loginUser)) {
            throw new DuplicateJoinException("이미 등록된 수강생이므로 중복 신청 불가합니다");
        }

        boolean hasEnrollment = this.enrollments.stream().anyMatch(e -> e.hasEnrollment(this.id, loginUser.getId()));
        if (hasEnrollment) {
            throw new DuplicateEnrollmentException("중복 수강 신청은 불가합니다");
        }

        if (this.state.isFinished() || this.recruitment.isNotRecruiting()) {
            throw new InvalidSessionJoinException("현재 수강 신청 불가 합니다");
        }

        if (this.sessionType.isFull(this.listener.size())) {
            throw new InvalidSessionJoinException("현재 수강 신청 인원이 모두 가득 찼습니다");
        }

        if (this.startDate == null || this.endDate == null || !isWithinPeriodRange(now)) {
            throw new InvalidSessionJoinException("현재 수강 신청 불가능한 기간 입니다");
        }
    }

    private boolean isWithinPeriodRange(LocalDateTime now) {
        return now.isAfter(this.startDate) && now.isBefore(this.endDate);
    }

    public Payment toPayment(NsUser loginUser, LocalDateTime now) {
        return new Payment(this.id, loginUser.getId(), this.sessionType.getAmount(), now);
    }

    public void join(NsUser loginUser, Payment payment) {
        validateJoin(loginUser, payment);
        addListener(loginUser);
    }

    private void validateJoin(NsUser loginUser, Payment payment) {
        validateNull(loginUser, payment);
        validatePayment(loginUser, payment);
    }

    private void validateNull(NsUser loginUser, Payment payment) {
        if (loginUser == null) {
            throw new NullPointerException("사용자 정보가 존재하지 않습니다");
        }

        if (payment == null) {
            throw new NullPointerException("결제 정보가 존재하지 않습니다");
        }
    }

    private void validatePayment(NsUser loginUser, Payment payment) {
        if (!payment.isPaymentComplete()) {
            throw new InvalidPaymentException("결제 완료 되지 않았습니다");
        }

        if (!payment.equalSessionId(this.id)) {
            throw new InvalidPaymentException("결제 정보와 강의 정보가 일치하지 않습니다");
        }

        if (!payment.equalNsUserId(loginUser)) {
            throw new InvalidPaymentException("결제 정보와 유저 정보가 일치하지 않습니다");
        }

        if (!this.sessionType.equalMoney(payment)) {
            throw new PaymentAmountMismatchException("결제 정보와 강의 금액이 일치하지 않습니다");
        }
    }

    public void addListener(NsUser loginUser) {
        if (listener.contains(loginUser)) {
            throw new DuplicateJoinException("이미 강의 등록된 회원입니다");
        }

        this.listener.add(loginUser);
    }

    public void update(Session target) {
        if (!matchId(target)) {
            throw new IllegalArgumentException("강의 정보가 일치하지 않습니다");
        }

        if (!matchCourseId(target)) {
            throw new IllegalArgumentException("코스 정보가 일치하지 않습니다");
        }

        this.title = target.title;
        this.state = target.state;
        this.recruitment = target.recruitment;
        this.sessionType = target.sessionType;
        this.startDate = target.startDate;
        this.endDate = target.endDate;
        this.updatedAt = target.updatedAt;
        this.selection = target.selection;
    }

    private boolean matchId(Session target) {
        return this.id.equals(target.id);
    }

    private boolean matchCourseId(Session target) {
        return this.courseId.equals(target.courseId);
    }

    public int getCount() {
        return this.listener.size();
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public SessionState getState() {
        return state;
    }

    public RecruitmentState getRecruitment() {
        return recruitment;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Set<NsUser> getListener() {
        return listener;
    }

    public CoverImages getCoverImages() {
        return coverImages;
    }

    public Selection getSelection() {
        return selection;
    }

    public int getCapacity() {
        return this.sessionType.getCapacity();
    }

    public long getAmount() {
        return this.sessionType.getAmount();
    }

    public void addAllCoverImages(List<CoverImage> coverImages) {
        this.coverImages.addAll(coverImages);
    }

    public boolean isAutomaticSelection() {
        return this.selection.isAutomatic();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Session session = (Session) other;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(title, session.title) && state == session.state && recruitment == session.recruitment && Objects.equals(sessionType, session.sessionType) && Objects.equals(startDate, session.startDate) && Objects.equals(endDate, session.endDate) && Objects.equals(listener, session.listener) && Objects.equals(coverImages, session.coverImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, title, state, recruitment, sessionType, startDate, endDate, listener, coverImages);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", state=" + state +
                ", recruitmentState=" + recruitment +
                ", sessionType=" + sessionType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", listener=" + listener +
                ", coverImages=" + coverImages +
                '}';
    }
}
