package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NsUserSessionsTest {

    public static final NsUserSession NS_USER_SESSION_1 = new NsUserSession(1L, 1L);
    public static final NsUserSession NS_USER_SESSION_2 = new NsUserSession(1L, 2L);
    public static final NsUserSession NS_USER_SESSION_3 = new NsUserSession(1L, 5L);
    public static final NsUserSession NS_USER_SESSION_4 = new NsUserSession(1L, 6L);
    public static final NsUserSession NS_USER_SESSION_1_APPROVED = new NsUserSession(1L, 1L, EnrollmentStatus.APPROVED);
    public static final NsUserSession NS_USER_SESSION_2_APPROVED = new NsUserSession(1L, 2L, EnrollmentStatus.APPROVED);
    public static final NsUserSession NS_USER_SESSION_4_APPROVED = new NsUserSession(1L, 6L, EnrollmentStatus.APPROVED);
    public static final NsUserSession NS_USER_SESSION_4_REJECTED = new NsUserSession(1L, 6L, EnrollmentStatus.REJECTED);
    public static final NsUserSessions NS_USER_SESSIONS = new NsUserSessions(NS_USER_SESSION_1_APPROVED, NS_USER_SESSION_2_APPROVED, NS_USER_SESSION_3);

    @Test
    @DisplayName("approvedUserNumber_승인,대기 섞인 목록_승인된 갯수만 반환")
    void approvedUserNumber() {
        assertThat(NS_USER_SESSIONS.approvedUserNumber()).isEqualTo(2L);
    }
}
