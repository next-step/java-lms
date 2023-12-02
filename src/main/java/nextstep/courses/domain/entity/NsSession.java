package nextstep.courses.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import nextstep.courses.domain.constant.SessionFee;
import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class NsSession {

    public static final int MAX_PAID_QUOTA = 30;

    private static final int FREE_QUOTA = 9999;
    private static final int FREE_AMOUNT = 0;
    private static final Random RANDOM = new Random();
    private static AtomicLong autoGenId = new AtomicLong(1L);

    private long id;

    private long courseId;

    private CoverImage coverImage;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private int quota;

    private int fee;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsSession() {}

    public NsSession(long courseId,
                     CoverImage coverImage,
                     SessionType sessionType,
                     SessionStatus sessionStatus,
                     LocalDate startDate,
                     LocalDate endDate,
                     int quota,
                     int fee) {
        validateImage(coverImage);
        validateSessionDate(startDate, endDate);

        this.id = autoGenId.getAndIncrement();
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quota = quota;
        this.fee = fee;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public static NsSession freeOf(long courseId,
                                   CoverImage coverImage,
                                   String sessionType,
                                   String sessionStatus,
                                   LocalDate startDate,
                                   LocalDate endDate) {
        return new NsSession(courseId,
                             coverImage,
                             SessionType.getType(sessionType),
                             SessionStatus.getType(sessionStatus),
                             startDate,
                             endDate,
                             FREE_QUOTA,
                             FREE_AMOUNT);
    }

    public static NsSession paidOf(long courseId,
                                   CoverImage coverImage,
                                   String sessionType,
                                   String sessionStatus,
                                   LocalDate startDate,
                                   LocalDate endDate,
                                   int fee) {
        return new NsSession(courseId,
                             coverImage,
                             SessionType.getType(sessionType),
                             SessionStatus.getType(sessionStatus),
                             startDate,
                             endDate,
                             RANDOM.nextInt(25) + 5,
                             fee);
    }

    public static NsSession paidOf(long courseId,
                                   CoverImage coverImage,
                                   String sessionType,
                                   String sessionStatus,
                                   LocalDate startDate,
                                   LocalDate endDate) {
        return new NsSession(courseId,
                             coverImage,
                             SessionType.getType(sessionType),
                             SessionStatus.getType(sessionStatus),
                             startDate,
                             endDate,
                             RANDOM.nextInt(25) + 5,
                             SessionFee.random().getFee());
    }

    public NsSession(long id,
                     long courseId,
                     CoverImage coverImage,
                     SessionType sessionType,
                     SessionStatus sessionStatus,
                     LocalDate startDate,
                     LocalDate endDate,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {
        validateImage(coverImage);
        validateSessionDate(startDate, endDate);

        this.id = id;
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public boolean available(Payment payment) {
        return isOpen() &&
               checkQuota() &&
               checkAmount(payment);
    }

    private boolean isOpen() {
        return SessionStatus.OPEN.equals(sessionStatus);
    }

    private boolean checkAmount(Payment payment) {
        return fee == payment.getAmount();
    }

    private boolean checkQuota() {
        return quota != 0;
    }

    private void validateSessionDate(LocalDate startDate, LocalDate endDate) {
        if (checkNull(startDate) || checkNull(endDate)) {
            throw new IllegalArgumentException("시작일과 종료일을 입력해주세요");
        }

        checkPeriod(startDate, endDate);
    }

    private boolean checkNull(LocalDate date) {
        return date == null;
    }

    private void checkPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일은 종료일보다 이후가 될 수 없습니다");
        }
    }

    private void validateImage(CoverImage coverImage) {
        if (coverImage == null) {
            throw new IllegalArgumentException("강의 커버 이미지를 넣어주세요");
        }
    }

    @Override
    public String toString() {
        return "NsSession{" +
               "id=" + id +
               ", courseId='" + courseId + '\'' +
               ", imageType ='" + coverImage.getImageType().getExtension() + '\'' +
               ", sessionType='" + sessionType + '\'' +
               ", sessionStatus='" + sessionStatus + '\'' +
               ", fee ='" + fee + '\'' +
               ", quota ='" + quota + '\'' +
               ", startDate='" + startDate + '\'' +
               ", endDate=" + endDate +
               '}';
    }

    public void decreaseQuota() {
        quota--;
    }
}
