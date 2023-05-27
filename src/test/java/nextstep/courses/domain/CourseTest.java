package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void register() {
        Course course = new Course(1L , List.of(sessionA(), sessionB()), "TDD, 클린 코드 with Java", 1L,
            LocalDateTime.now(), LocalDateTime.now());
        assertThat(course.register(userA(), LocalDateTime.now())).containsExactly(1L, 2L);
    }

    private static Session sessionA() {
        return new Session(1L, LocalDateTime.MIN, LocalDateTime.MAX, null, true, SessionStatus.Recruiting,
            30);
    }
    private static Session sessionB() {
        return new Session(2L, LocalDateTime.MIN, LocalDateTime.MAX, null, true, SessionStatus.Recruiting,
            30);
    }

    private static NsUser userA() {
        return new NsUser(1L, "A", "A", "A", "A@A.A");
    }


}
