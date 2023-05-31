package nextstep;

import nextstep.courses.domain.*;

import java.time.LocalDateTime;

public class Fixtures {
    private static final LocalDateTime startedAt = LocalDateTime.now();
    private static final LocalDateTime endedAt = LocalDateTime.now().plusDays(30);
    private static final String sessionCoverImage = "https://edu.nextstep.camp/images/covers/basic/008.jpg";
    private static final SessionCostType sessionCostType = SessionCostType.PAID;
    private static final SessionStatus sessionStatus = SessionStatus.RECRUITING;
    private static final int maxUserCount = 30;

    public static SessionBuilder aSession() {
        return SessionBuilder.aSession()
                .withSessionRegistration(aSessionRegistrationBuilder().build())
                .withSessionPeriod(new SessionPeriod(startedAt, endedAt))
                .withSessionCoverImage(sessionCoverImage)
                .withSessionCostType(sessionCostType);
    }

    public static SessionRegistrationBuilder aSessionRegistrationBuilder() {
        return SessionRegistrationBuilder.aSessionRegistrationBuilder()
                .withSessionStatus(sessionStatus)
                .withMaxUserCount(maxUserCount);
    }
}
