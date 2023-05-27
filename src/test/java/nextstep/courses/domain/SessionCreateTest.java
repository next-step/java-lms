package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("session 생성 테스트")
class SessionCreateTest {
    private final Image image = Image.createImage("이미지 입니다");

    @BeforeEach
    public void initialize() {
        SimpleIdGenerator.initialize();
    }

    @Test
    @DisplayName("객체 생성 테스트")
    void 객체_생성_테스트() {
        Session session = Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE
        );

        assertAll(
                () -> assertThat(session).isNotNull(),
                () -> assertThat(session).isInstanceOf(Session.class)
        );
    }

    @Test
    @DisplayName("강의 생성시 강의 상태에 값을 주지 않으면 준비중으로 세팅한다")
    void 강의_상태_default_준비중() {
        Session session = Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                null,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE
        );


        Session createdSession = Session.of(
                1,
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE,
                1
        );

        assertAll(
                () -> assertThat(session).isNotNull(),
                () -> assertThat(createdSession).isNotNull(),
                () -> assertThat(session).isEqualTo(createdSession)
        );
    }

    @Test
    @DisplayName("객체 동등성 생성 테스트")
    void 객체_생성_동등성_테스트() {
        Session session = Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE
        );

        Session createdSession = Session.of(
                1,
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE,
                1
        );


        assertAll(
                () -> assertThat(session).isNotNull(),
                () -> assertThat(createdSession).isNotNull(),
                () -> assertThat(session).isEqualTo(createdSession)
        );
    }

}