package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @Test
    void create() {
        Course course = new Course("넥스트스텝", 1L);
        Session session = new Session(course, 1, "우하하하하", SessionType.FREE, SessionStatus.WAITING, 10, LocalDateTime.now(), LocalDateTime.now().plusDays(5));
        assertThat(session).isNotNull();
        assertThat(session.getCourse().getTitle()).isEqualTo("넥스트스텝");
        assertThat(session.getCoverImage()).isEqualTo("우하하하하");
        assertThat(session.getType()).isEqualTo(SessionType.FREE);
        assertThat(session.getStatus()).isEqualTo(SessionStatus.WAITING);
        assertThat(session.getStatus()).isNotEqualTo(SessionStatus.RECRUIT);
    }

}
