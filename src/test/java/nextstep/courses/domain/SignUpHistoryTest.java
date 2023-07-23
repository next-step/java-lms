package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;
import nextstep.users.domain.NextStepUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpHistoryTest {

    @DisplayName("수강 신청 기록 생성")
    @Test
    void create() {

        Course course = new Course("넥스트스텝", 1L);
        Session session = new Session(
                course,
                new SessionEssentialInfo(
                        1,
                        "우하하하하",
                        0
                ),
                SessionType.FREE,
                SessionStatus.RECRUIT,
                new SessionPeriod(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(5)
                )
        );
        NextStepUser JAVAJIGI = new NextStepUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        SignUpHistory signUpHistory = new SignUpHistory(session, JAVAJIGI);

        assertThat(signUpHistory.getSession()).isEqualTo(session);
        assertThat(signUpHistory.getUser()).isEqualTo(JAVAJIGI);


    }

}
