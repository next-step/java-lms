package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.sessionType.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

import static nextstep.courses.ExceptionMessage.SESSION_ENROLL_FAIL_MESSAGE;
import static nextstep.courses.domain.session.SessionStatus.PREPARING;

public class Session {
    private Long id;
    private String title;
    private String description;
    private CoverImage coverImage;
    private SessionType sessionType;
    private SessionStatus sessionStatus = PREPARING;
    private Period periodOfSession;
    private Course course;
    private EnrolledUsers enrolledUsers = new EnrolledUsers();
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Session(Long id, String title, String description, CoverImage coverImage, SessionType sessionType, Period periodOfSession) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.periodOfSession = periodOfSession;
    }

    public void enroll(NsUser user, Payment payment) {
        validateSessionEnrollment(user, payment);
        addEnrolledUse(user);
    }

    private void validateSessionEnrollment(NsUser user, Payment payment) {
        if (!isSessionEnrollPossible(user, payment)) {
            throw new CannotEnrollException(SESSION_ENROLL_FAIL_MESSAGE.message());
        }
    }

    private boolean isSessionEnrollPossible(NsUser user, Payment payment) {
        return sessionStatus.isEnrollPossibleStatus() &&
                !enrolledUsers.isDuplicatedUser(user) &&
                sessionType.isSessionNotFull(enrolledUsers.numberOfCurrentEnrollment()) &&
                sessionType.isValidPayment(payment);
    }

    private void addEnrolledUse(NsUser user) {
        enrolledUsers.add(user);
    }

    public void updateStatusAs(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
