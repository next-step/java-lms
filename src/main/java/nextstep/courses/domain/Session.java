package nextstep.courses.domain;

import nextstep.users.domain.User;

import java.time.LocalDate;

public class Session {
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

    public static Session paidOf(String title, Long creatorId,
                                 double imageSize, int width, int height, String imageType,
                                 LocalDate startDate, LocalDate endDate,
                                 SessionStatus sessionStatus, int capacity,
                                 int price) {
        return new Session(new SessionInfo(title, creatorId),
                new ImageCover(imageSize, width, height, imageType),
                new SessionPeriod(startDate, endDate),
                new Enrollment(sessionStatus, capacity),
                new Charge(ChargeStatus.PAID, price));
    }

    public static Session freeOf(String title, Long creatorId,
                                 double imageSize, int width, int height, String imageType,
                                 LocalDate startDate, LocalDate endDate,
                                 SessionStatus sessionStatus) {
        return new Session(new SessionInfo(title, creatorId),
                new ImageCover(imageSize, width, height, imageType),
                new SessionPeriod(startDate, endDate),
                new Enrollment(sessionStatus, Integer.MAX_VALUE),
                new Charge(ChargeStatus.FREE));
    }

    public void enroll(User user) {
        enrollment.enroll(user);
    }
}
