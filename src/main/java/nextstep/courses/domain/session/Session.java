package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.SessionTypeEnum;
import nextstep.courses.domain.session.constant.StatusEnum;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.EnrollmentRequest;
import nextstep.courses.domain.session.enrollment.EnrollmentResponse;
import nextstep.courses.domain.session.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private long fee;
    private StatusEnum status;
    private SessionTypeEnum type;
    private SessionDateTime dateTime;
    private Image image;
    private Enrollment enrollment;

    public static Session freeSession(LocalDateTime startDate, LocalDateTime endDate) {
        return new Session(0L, 0, SessionTypeEnum.FREE, startDate, endDate, 0, null);
    }

    public static Session paidSession(long fee, LocalDateTime startDate, LocalDateTime endDate, int maxEnrollmentCount) {
        return new Session(0L, fee, SessionTypeEnum.PAID, startDate, endDate, maxEnrollmentCount, null);
    }

    public Session(Long id,
                   long fee,
                   SessionTypeEnum type,
                   LocalDateTime startDate,
                   LocalDateTime endDate,
                   int maxEnrolledCount,
                   Image image) {
           this.id = id;
           this.fee = fee;
           status = StatusEnum.READY;
           this.type = type;
           this.dateTime = new SessionDateTime(startDate, endDate);
           enrollment = new Enrollment(maxEnrolledCount);
           this.image = image;
    }

    public void addImage(Image image) {
        this.image = image;
    }

    public void open() {
        status = StatusEnum.OPEN;
    }

    public Payment enroll(NsUser user, long amount) {
        checkEnrollmentAvailability();
        if (type == SessionTypeEnum.FREE) {
            return enrollFreeSession(user, amount);
        }
        return enrollPaidSession(user, amount);
    }

    private void checkEnrollmentAvailability() {
        if (status != StatusEnum.OPEN) {
            throw new IllegalArgumentException("강의를 신청할 수 없습니다. 강의가 모집 중일 때 신청해주세요.");
        }
    }

    private Payment enrollFreeSession(NsUser user, long amount) {
        EnrollmentResponse response = enrollment.enrollFreeSession(new EnrollmentRequest(id, user, fee, amount));
        return new Payment(response.getRandomUUID(), id, user.getId(), amount);
    }

    private Payment enrollPaidSession(NsUser user, long amount) {
        EnrollmentResponse response = enrollment.enrollPaidSession(new EnrollmentRequest(id, user, fee, amount));
        if (response.isMaxStudent()) {
            status = StatusEnum.CLOSED;
        }
        return new Payment(response.getRandomUUID(), id, user.getId(), amount);
    }

    public int getCurrentEnrolledCount() {
        return enrollment.currentStudentCount();
    }
}
