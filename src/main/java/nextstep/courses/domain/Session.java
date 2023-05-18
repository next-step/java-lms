package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.users.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session extends BaseEntity {
    private Long id;

    private String period;

    private Image coverImage;

    private LocalDateTime openingDateTime;

    private LocalDateTime closingDateTime;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private List<User> users = new ArrayList<>();

    private int maximumEnrollment;

    protected Session() {
    }

    public static Session create(String period, Image coverImage,
                                 LocalDateTime openingDateTime, LocalDateTime closingDateTime,
                                 SessionType sessionType, SessionStatus sessionStatus,
                                 int maximumEnrollment) throws InvalidSessionDateTimeException {
        checkDateTime(openingDateTime, closingDateTime);

        Session session = new Session();
        session.period = period;
        session.coverImage = coverImage;
        session.openingDateTime = openingDateTime;
        session.closingDateTime = closingDateTime;
        session.sessionType = sessionType;
        session.sessionStatus = sessionStatus;
        session.maximumEnrollment = maximumEnrollment;

        return session;
    }

    public void enroll(User user) throws SessionEnrollmentException {
        checkSessionStatus();
        checkEnrollment();

        this.users.add(user);
    }

    private void checkSessionStatus() throws SessionEnrollmentException {
        if (this.sessionStatus != SessionStatus.RECRUITING) {
            throw new SessionEnrollmentException(String.format("현재 강의 상태는 '%s'이며, '%s' 상태에서만 수강 신청이 가능합니다.",
                    this.sessionStatus, SessionStatus.RECRUITING));
        }
    }

    private void checkEnrollment() throws SessionEnrollmentException {
        if (this.users.size() >= this.maximumEnrollment) {
            throw new SessionEnrollmentException(String.format("최대 수강 인원인 '%d명'을 초과했습니다.", maximumEnrollment));
        }
    }

    private static void checkDateTime(LocalDateTime openingDateTime, LocalDateTime closingDateTime) throws InvalidSessionDateTimeException {
        if (openingDateTime.isAfter(closingDateTime)) {
            throw new InvalidSessionDateTimeException("강의 시작 시간은 종료 시간보다 늦을 수 없습니다.");
        }
    }
}
