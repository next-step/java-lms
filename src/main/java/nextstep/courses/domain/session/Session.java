package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.CoverImages;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;

public abstract class Session {
    public static final String SESSION_NOT_OPENED = "강의 신청 가능 기한이 아닙니다.";
    public static final String ENROLLMENT_ALREADY_DONE = "이미 수강 신청을 완료하신 강의입니다.";
    public static final String PAYMENT_NOT_MATCHING = "결제 금액과 수강료가 일치해야 합니다.";

    protected final Long sessionId;
    private final SessionPeriod sessionPeriod;
    private CoverImages coverImages;
    private final SessionStatusEnum sessionStatus;
    private boolean isOpenForEnrollment;
    protected int maxEnrollments;
    protected int numberOfStudents;
    protected Long fee;

    protected Session(Long sessionId, SessionPeriod sessionPeriod, List<CoverImage> coverImages,
                      SessionStatusEnum sessionStatus, int numberOfStudents, boolean isOpenForEnrollment,
                      Long fee) {
        this.sessionId = sessionId;
        this.sessionPeriod = sessionPeriod;
        this.coverImages = CoverImages.of(coverImages);
        this.sessionStatus = sessionStatus;
        this.numberOfStudents = numberOfStudents;
        this.isOpenForEnrollment = isOpenForEnrollment;
        this.fee = fee;
    }

    protected Session(Long sessionId, SessionPeriod sessionPeriod, List<CoverImage> coverImages,
                      SessionStatusEnum sessionStatus, int numberOfStudents, boolean isOpenForEnrollment,
                      int maxEnrollments, Long fee) {
        this.sessionId = sessionId;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImages = CoverImages.of(coverImages);
        this.maxEnrollments = maxEnrollments;
        this.numberOfStudents = numberOfStudents;
        this.isOpenForEnrollment = isOpenForEnrollment;
        this.fee = fee;
    }


    protected boolean isSessionOpened() {
        return sessionStatus.isSessionOpened();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public List<CoverImage> getCoverImages() {
        return coverImages.getCoverImages();
    }

    public void addCoverImages(CoverImages coverImages) {
        this.coverImages = coverImages;
    }

    public void addCoverImage(CoverImage coverImage) {
        this.coverImages.addCoverImage(coverImage);
    }

    public SessionStatusEnum getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxEnrollments() {
        return maxEnrollments;
    }

    public int getNumberOfStudents() { return numberOfStudents; }

    public Long getFee() {
        return fee;
    }

    public boolean isOpenForEnrollment() {
        return isOpenForEnrollment;
    }

    public abstract void enrollStudent(NsUser user, Payment payment);
}
