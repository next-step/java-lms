package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("session 기능 테스트")
class SessionMethodTest {
    private final Image image = Image.from("이미지 입니다");
    private final Image changeImage = Image.from("반갑습니다");

    @BeforeEach
    public void initialize() {
        SimpleIdGenerator.initialize();
    }


    @ParameterizedTest(name = "이미지 변경 실패 테스트 - {index}. {2}")
    @MethodSource("이미지_타입_변경_실패")
    @DisplayName("이미지 변경 실패 테스트")
    void 이미지_변경_실패_테스트(NsUser requestUser, String expectedExceptionMessage, String testDisplayName) {
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


        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> session.changeImage(changeImage, requestUser));
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    static Stream<Arguments> 이미지_타입_변경_실패() {
        return Stream.of(
                Arguments.arguments(null, "요청자가 입력되질 않았어요 :(", "요청자가 입력되질 않았다면 예외를 던진다"),
                Arguments.arguments(NsUserTest.SANJIGI, "강의자만 이미지 변경 가능해요 :( 강의자 [javajigi]", "강의자가 아니라면 예외를 던진다")
        );
    }


    @Test
    @DisplayName("이미지 변경 성공 테스트")
    void 이미지_변경_성공_테스트() {
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
                changeImage,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE,
                1
        );

        assertAll(
                () -> assertThat(session).isNotNull(),
                () -> assertThat(createdSession).isNotNull(),
                () -> assertThat(session.changeImage(changeImage, NsUserTest.JAVAJIGI)).isEqualTo(createdSession)
        );
    }

    @ParameterizedTest(name = "강의 타입 변경 실패 테스트 - {index}. {5}")
    @MethodSource("강의_타입_변경_실패")
    @DisplayName("강의 타입 변경 실패 테스트")
    void 강의_타입_변경_실패_테스트(NsUser requestUser, String expectedExceptionMessage, SessionState sessionState, SessionState recruitmentState, Class<? extends Throwable> expectedType, String testDisplayName) {
        Session session = Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                sessionState,
                recruitmentState,
                SessionType.FREE
        );


        Throwable exception = Assertions.assertThrows(expectedType, () -> session.changeSessionType(SessionType.FREE, requestUser));
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    static Stream<Arguments> 강의_타입_변경_실패() {
        return Stream.of(
                Arguments.arguments(null, "요청자가 입력되질 않았어요 :(", SessionState.PROGRESSING, SessionState.END_OF_RECRUITMENT, IllegalArgumentException.class, "요청자가 입력되질 않았다면 예외를 던진다"),
                Arguments.arguments(NsUserTest.SANJIGI, "강의자만 강의 타입 변경 가능해요 :( 강의자 [javajigi]", SessionState.PROGRESSING, SessionState.END_OF_RECRUITMENT, IllegalArgumentException.class, "강의자가 아니라면 예외를 던진다"),
                Arguments.arguments(NsUserTest.JAVAJIGI, "강의 타입 변경은 강의 준비중일때만 가능해요 :(", SessionState.PROGRESSING, SessionState.END_OF_RECRUITMENT, IllegalStateException.class, "강의 타입이 준비중이 아니라면 예외를 던진다"),
                Arguments.arguments(NsUserTest.JAVAJIGI, "수강생 모집중에는 강의 타입을 변경할 수 없어요 :(", SessionState.PREPARING, SessionState.RECRUITING, IllegalStateException.class, "모집 타입이 모집완료가 아니라면 예외를 던진다")
        );
    }

    @Test
    @DisplayName("강의 타입 변경 성공 테스트")
    void 강의_타입_변경_성공_테스트() {
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
                changeImage,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.PAID,
                1
        );

        assertAll(
                () -> assertThat(session).isNotNull(),
                () -> assertThat(createdSession).isNotNull(),
                () -> assertThat(session.changeSessionType(SessionType.PAID, NsUserTest.JAVAJIGI)).isEqualTo(createdSession)
        );
    }

    @ParameterizedTest(name = "모집 상태 변경 실패 테스트 - {index}. {3} 제공된 모집 상태 : [{2}]")
    @MethodSource("모집_상태_변경_실패")
    @DisplayName("모집 상태 변경 실패 테스트")
    void 모집_상태_변경_실패_테스트(NsUser requestUser, String expectedExceptionMessage, SessionState recruitmentState, String testDisplayName) {
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


        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> session.changeRecruitmentState(recruitmentState, requestUser));
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    static Stream<Arguments> 모집_상태_변경_실패() {
        return Stream.of(
                Arguments.arguments(null, "요청자가 입력되질 않았어요 :(", SessionState.END_OF_RECRUITMENT, "요청자가 입력되질 않았다면 예외를 던진다"),
                Arguments.arguments(NsUserTest.SANJIGI, "강의자만 모집 상태 변경 가능해요 :( 강의자 [javajigi]", SessionState.END_OF_RECRUITMENT, "강의자가 아니라면 예외를 던진다"),
                Arguments.arguments(NsUserTest.JAVAJIGI, "모집 상태에 값이 입력 되지 않았어요 :(", null, "강의 타입이 준비중이 아니라면 예외를 던진다"),
                Arguments.arguments(NsUserTest.JAVAJIGI, "모집 상태는 모집중/모집 종료만 가능해요 :( 제공된 모집 상태 [PREPARING]", SessionState.PREPARING, "모집 타입이 모집완료가 아니라면 예외를 던진다"),
                Arguments.arguments(NsUserTest.JAVAJIGI, "모집 상태는 모집중/모집 종료만 가능해요 :( 제공된 모집 상태 [PROGRESSING]", SessionState.PROGRESSING, "모집 타입이 모집완료가 아니라면 예외를 던진다"),
                Arguments.arguments(NsUserTest.JAVAJIGI, "모집 상태는 모집중/모집 종료만 가능해요 :( 제공된 모집 상태 [FINISH]", SessionState.FINISH, "모집 타입이 모집완료가 아니라면 예외를 던진다")
        );
    }

    @Test
    @DisplayName("모집 상태 변경 성공 테스트")
    void 모집_상태_변경_성공_테스트() {
        Session session = Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.RECRUITING,
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
                SessionState.RECRUITING,
                SessionType.FREE,
                1
        );

        assertAll(
                () -> assertThat(session).isNotNull(),
                () -> assertThat(createdSession).isNotNull(),
                () -> assertThat(session.changeRecruitmentState(SessionState.RECRUITING, NsUserTest.JAVAJIGI)).isEqualTo(createdSession)
        );
    }

}