package nextstep.courses.domain.fixture;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class SessionFixtures {
    public static final NsUser USER_1 = new NsUser(1L, "user1", "password", "name", "user1@test.test");
    public static final NsUser USER_2 = new NsUser(2L, "user2", "password", "name", "user2@test.test");
    public static CoverImage COVER_IMAGE = new CoverImage(1L, "https://test.test/images/0");
    public static final Session SESSION = new Session(
            2L,
            "hello",
            COVER_IMAGE,
            new SessionPeriod(
                    LocalDate.of(2023, 5, 24),
                    LocalDate.of(2023, 5, 25)
            ),
            SessionType.FREE,
            SessionStatus.PREPARING,
            10
    );

}
