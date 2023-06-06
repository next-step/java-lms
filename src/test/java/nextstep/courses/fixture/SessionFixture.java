package nextstep.courses.fixture;

import nextstep.courses.domain.*;

import java.time.LocalDateTime;

public class SessionFixture {
    public static Session createRecruitingSession() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endAt = startedAt.plusMonths(1);
        SessionStatus sessionStatus = new SessionStatus(SessionProgressStatus.PROGRESSING, SessionRecruitmentStatus.RECRUITING);
        return new Session(1L, new SessionPeriod(startedAt, endAt), PaymentType.PAID, sessionStatus, 10, new SessionImageUrl("https://image.test.com"));
    }

    public static Session create(SessionStatus sessionStatus, int maximumUserCount) {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endAt = startedAt.plusMonths(1);

        return new Session(1L, new SessionPeriod(startedAt, endAt), PaymentType.PAID, sessionStatus, maximumUserCount, new SessionImageUrl("https://image.test.com"));
    }

    public static Session create(SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus, int maximumUserCount) {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endAt = startedAt.plusMonths(1);

        return new Session(1L, new SessionPeriod(startedAt, endAt), PaymentType.PAID, new SessionStatus(sessionProgressStatus, sessionRecruitmentStatus), maximumUserCount, new SessionImageUrl("https://image.test.com"));
    }
}
