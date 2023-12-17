package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.SessionTypeEnum;
import nextstep.courses.domain.session.constant.StatusEnum;
import nextstep.courses.domain.session.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {
    private Long id;
    private long fee;
    private StatusEnum status;
    private SessionTypeEnum type;
    private SessionDateTime dateTime;
    private StudentManager studentManager;
    private Image image;
    private UUID uuid = UUID.randomUUID();

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
           studentManager = new StudentManager(maxEnrolledCount);
           this.image = image;
    }

    public void addImage(Image image) {
        this.image = image;
    }

    public Payment enroll(NsUser user, int amount) {
        checkEnrollmentAvailability();
        if (type == SessionTypeEnum.FREE) {
            return enrollFreeSession(user);
        }
        return enrollPaidSession(user, amount);
    }

    private void checkEnrollmentAvailability() {
        if (!canEnroll()) {
            throw new IllegalArgumentException("강의를 신청할 수 없습니다. 강의가 모집 중일 때 신청해주세요.");
        }
    }

    private boolean canEnroll() {
        if (status == StatusEnum.OPEN) {
            return true;
        }
        return false;
    }

    private Payment enrollFreeSession(NsUser user) {
        studentManager.add(user);
        return new Payment(uuid.toString(), id, user.getId(), 0L);
    }

    private Payment enrollPaidSession(NsUser user, int amount) {
        checkPaidSessionAvailability(amount);
        studentManager.add(user);
        if (studentManager.isMaxStudent()) {
            status = StatusEnum.CLOSED;
        }
        return new Payment(uuid.toString(), id, user.getId(), fee);
    }

    private void checkPaidSessionAvailability(int amount) {
        if (amount < fee) {
            throw new IllegalArgumentException("결제한 금액이 수강료보다 작습니다.");
        }
    }

    public void open() {
        status = StatusEnum.OPEN;
    }

    public int getCurrentEnrolledCount() {
        return studentManager.getStudentCount();
    }
}
