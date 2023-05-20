package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;
import nextstep.users.domain.NextStepUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpHistoryTest {

    @Test
    void create() {

        Course course = new Course("넥스트스텝", 1L);
        Session session = new Session(course, 1, "우하하하하", SessionType.FREE, SessionStatus.WAITING, 10, LocalDateTime.now(), LocalDateTime.now().plusDays(5));
        NextStepUser JAVAJIGI = new NextStepUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        SignUpHistory signUpHistory = new SignUpHistory(session, JAVAJIGI);

        assertThat(signUpHistory.getSession()).isEqualTo(session);
        assertThat(signUpHistory.getUser()).isEqualTo(JAVAJIGI);


    }

}
