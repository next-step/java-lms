package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.User;

import java.time.LocalDate;

public class Session {
    public static final String NOT_FOUND_SESSION_ERROR_MESSAGE =
            "수강 신청이 허용되지 않습니다. 결제 금액이 세션 가격과 일치하지 않습니다.";
    private SessionInfo sessionInfo;
    private ImageCover imageCover;
    private SessionPeriod sessionPeriod;
    private Enrollment enrollment;
    private Charge charge;

    private Session(SessionInfo sessionInfo, ImageCover imageCover,
                    SessionPeriod sessionPeriod, Enrollment enrollment, Charge charge) {
        this.sessionInfo = sessionInfo;
        this.imageCover = imageCover;
        this.sessionPeriod = sessionPeriod;
        this.enrollment = enrollment;
        this.charge = charge;
    }

    public static Session paidOf(String title, Long sessionId,
                                 double imageSize, int width, int height, String imageType,
                                 LocalDate startDate, LocalDate endDate,
                                 SessionStatus sessionStatus, int capacity,
                                 long price) {
        return new Session(new SessionInfo(title, sessionId),
                new ImageCover(imageSize, width, height, imageType),
                new SessionPeriod(startDate, endDate),
                new Enrollment(sessionStatus, capacity),
                new Charge(ChargeStatus.PAID, price));
    }

    public static Session freeOf(String title, Long sessionId,
                                 double imageSize, int width, int height, String imageType,
                                 LocalDate startDate, LocalDate endDate,
                                 SessionStatus sessionStatus) {
        return new Session(new SessionInfo(title, sessionId),
                new ImageCover(imageSize, width, height, imageType),
                new SessionPeriod(startDate, endDate),
                new Enrollment(sessionStatus, Integer.MAX_VALUE),
                new Charge(ChargeStatus.FREE));
    }

    public void enroll(User user, Payment payment) {
        if (!charge.isCompletePay(payment)) {
            throw new IllegalArgumentException(NOT_FOUND_SESSION_ERROR_MESSAGE);
        }
        enrollment.enroll(user);
    }
}
