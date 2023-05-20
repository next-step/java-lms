package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;
import nextstep.users.domain.NextStepUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private Course course = new Course("넥스트스텝", 1L);
    private Session session = new Session(course, 1, "우하하하하", SessionType.FREE, SessionStatus.WAITING, 0, LocalDateTime.now(), LocalDateTime.now().plusDays(5));
    private NextStepUser JAVAJIGI = new NextStepUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

    @Test
    void create() {

        assertThat(session).isNotNull();
        assertThat(session.getCourse().getTitle()).isEqualTo("넥스트스텝");
        assertThat(session.getCoverImage()).isEqualTo("우하하하하");
        assertThat(session.getType()).isEqualTo(SessionType.FREE);
        assertThat(session.getStatus()).isEqualTo(SessionStatus.WAITING);
        assertThat(session.getStatus()).isNotEqualTo(SessionStatus.RECRUIT);
    }

    @ParameterizedTest
    @ValueSource(strings = "{a}")
    void addSignUpHistory(String input) {

        assertThatThrownBy(() -> {
            session.addSignUpHistory(new SignUpHistory(session, JAVAJIGI));
        }).hasMessageContaining("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");

        session = new Session(course, 1, "우하하하하", SessionType.FREE, SessionStatus.RECRUIT, 0, LocalDateTime.now(), LocalDateTime.now().plusDays(5));

        assertThatThrownBy(() -> {
            session.addSignUpHistory(new SignUpHistory(session, JAVAJIGI));
        }).hasMessageContaining("최대 수강 인원을 초과할 수 없습니다.");

    }

}
