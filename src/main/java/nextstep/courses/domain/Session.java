package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;
    private Course course;
    private SessionProgressStatus progressStatus;
    private SessionRecruitmentStatus recruitmentStatus;
    private SessionType type;
    private Money price;
    private Capacity maxCapacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionCoverImage coverImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Long id, Course course, SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus, SessionType type, Money price, Capacity maxCapacity, LocalDate startDate, LocalDate endDate, SessionCoverImage coverImage, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (progressStatus == SessionProgressStatus.ENDED && recruitmentStatus == SessionRecruitmentStatus.RECRUITING) {
            throw new IllegalArgumentException("종료된 강의는 모집중 상태일 수 없습니다.");
        }

        this.id = id;
        this.course = course;
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.type = type;
        this.price = price;
        this.maxCapacity = maxCapacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session(SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus, SessionType type, Money price, Capacity maxCapacity, LocalDate startDate, LocalDate endDate, SessionCoverImage coverImage) {
        this(null, null, progressStatus, recruitmentStatus, type, price, maxCapacity, startDate, endDate, coverImage, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(Long sessionId) {
        this.id = sessionId;
    }

    public static Session createFreeSession(LocalDate startDate, LocalDate endDate) {
        return new Session(
                SessionProgressStatus.READY,
                SessionRecruitmentStatus.NOT_RECRUITING,
                SessionType.FREE,
                Money.FREE,
                Capacity.ZERO,
                startDate,
                endDate,
                SessionCoverImage.EMPTY
        );
    }

    public static Session createPaidSession(Money price, Capacity maxCapacity, LocalDate startDate, LocalDate endDate) {
        return new Session(
                SessionProgressStatus.READY,
                SessionRecruitmentStatus.NOT_RECRUITING,
                SessionType.PAID,
                price,
                maxCapacity,
                startDate,
                endDate,
                SessionCoverImage.EMPTY
        );
    }

    public Enrollment enroll(int currentCount, Student student, Payment payment) {
        validateEnrollmentAvailable();
        validateMaxCapacity(currentCount);
        validatePayment(payment);

        return Enrollment.request(this, student);
    }

    public void updateCoverImage(SessionCoverImage newCoverImage) {
        coverImage = newCoverImage;
    }

    public void readySession() {
        progressStatus = SessionProgressStatus.READY;
    }

    public void startSession() {
        progressStatus = SessionProgressStatus.ONGOING;
    }

    public void endSession() {
        progressStatus = SessionProgressStatus.ENDED;
        stopRecruitment();
    }

    public void startRecruitment() {
        if (progressStatus == SessionProgressStatus.ENDED) {
            throw new IllegalStateException("종료된 강의는 수강생 모집이 불가합니다.");
        }

        recruitmentStatus = SessionRecruitmentStatus.RECRUITING;
    }

    public void stopRecruitment() {
        recruitmentStatus = SessionRecruitmentStatus.NOT_RECRUITING;
    }

    private void validateEnrollmentAvailable() {
        if (recruitmentStatus == SessionRecruitmentStatus.NOT_RECRUITING) {
            throw new IllegalStateException("본 강의는 수강생을 모집하고 있지 않습니다.");
        }

        if (progressStatus == SessionProgressStatus.ENDED) {
            throw new IllegalStateException("본 강의는 종료된 강의입니다.");
        }
    }

    private void validateMaxCapacity(int currentCount) {
        if (type == SessionType.PAID && maxCapacity.isFull(currentCount)) {
            throw new IllegalArgumentException("최대 수강 인원(" + maxCapacity.value() + "명)에 도달하여 수강 신청이 불가능합니다.");
        }
    }

    private void validatePayment(Payment payment) {
        if (payment.notMatches(price.amount())) {
            throw new IllegalArgumentException("결제한 금액과 수강료가 일치하지 않습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public SessionProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public SessionType getType() {
        return type;
    }

    public Money getPrice() {
        return price;
    }

    public Capacity getMaxCapacity() {
        return maxCapacity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void assignId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Session session = (Session) object;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
