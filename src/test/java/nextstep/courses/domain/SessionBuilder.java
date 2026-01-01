package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionBuilder {

    private Long id;
    private ImageFile imageFile;
    private SessionPeriod period;
    private SessionRecruitingStatus recruitingStatus;
    private SessionProgressStatus progressStatus;
    private EnrollmentRule enrollmentRule;
    private Enrollments enrollments;

    private SessionBuilder() {
        // ✅ 테스트에 바로 쓸 수 있는 기본값
        this.id = 1L;
        this.imageFile = new ImageFile(1024 * 1024, "png", 300, 200);
        this.period = new SessionPeriod(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7)
        );
        this.recruitingStatus = SessionRecruitingStatus.RECRUITING;
        this.progressStatus = SessionProgressStatus.READY;
        this.enrollmentRule = new FreeEnrollmentRule();
        this.enrollments = new Enrollments();
    }

    public static SessionBuilder builder() {
        return new SessionBuilder();
    }

    // ===== 필드 단위 설정 =====

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withImageFile(ImageFile imageFile) {
        this.imageFile = imageFile;
        return this;
    }

    public SessionBuilder withPeriod(SessionPeriod period) {
        this.period = period;
        return this;
    }

    public SessionBuilder withProgressStatus(SessionProgressStatus status) {
        this.progressStatus = status;
        return this;
    }

    public SessionBuilder withRecruitingStatus(SessionRecruitingStatus status) {
        this.recruitingStatus = status;
        return this;
    }

    public SessionBuilder withEnrollmentRule(EnrollmentRule enrollmentRule) {
        this.enrollmentRule = enrollmentRule;
        return this;
    }

    public SessionBuilder withEnrollments(Enrollments enrollments) {
        this.enrollments = enrollments;
        return this;
    }

    // ===== 생성 =====

    public Session build() {
        return new Session(
                id,
                imageFile,
                period,
                recruitingStatus,
                progressStatus,
                enrollmentRule,
                enrollments
        );
    }
}
