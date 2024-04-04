package nextstep.session.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SessionsTest {

    private Session session;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        FilePathInformation filePathInformation = new FilePathInformation("/home", "mapa", "jpg");
        Course course = new Course("Course1", 1L, 3);

        session = new FreeSession(
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, filePathInformation, 10000),
                "얼른 배우자 객체지향",
                course,
                1L,
                new Tutor(NsUserTest.JAVAJIGI)
        );
    }

    @DisplayName("새로운 세션을 추가할 수 있다.")
    @Test
    void addNewSession() {
        // given
        Sessions sessions = new Sessions();

        // when
        sessions.add(session);

        // then
        Assertions.assertThat(sessions.countSessions()).isEqualTo(1);
    }
}
