package nextstep.users.domain;

import nextstep.courses.domain.NsUserSession;
import nextstep.courses.domain.NsUserSessions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TeacherTest {
    public static final NsUserSession NS_USER_SESSION_1 = new NsUserSession(1L, 1L);
    public static final NsUserSession NS_USER_SESSION_2 = new NsUserSession(2L, 1L);
    public static final NsUserSession NS_USER_SESSION_3 = new NsUserSession(2L, 2L);
    public static final NsUserSessions SELECTED_STUDENTS = new NsUserSessions(List.of(NS_USER_SESSION_1, NS_USER_SESSION_2, NS_USER_SESSION_3));

    @Test
    @DisplayName("register_선발된 내역_선발된 내역의 sessionId, nsUserId 일치하지 않으면 throw IllegalArgumentException")
    void 수강생_등록() {
        Teacher teacher = new Teacher(SELECTED_STUDENTS);
        assertDoesNotThrow(() -> teacher.register(1L, 1L));
        assertThatThrownBy(() -> teacher.register(2L, 2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선발되지 않은 학생입니다. 수강신청이 취소됩니다.");
    }
}
