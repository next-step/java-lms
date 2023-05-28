package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("session 생성 예외 발생 테스트")
class SessionCreateExceptionTest {
    private final Image image = Image.createImage("이미지 입니다");

    @Test
    @DisplayName("정원이 0명이라면 예외를 던진다")
    void 정원_0명일_경우() {


        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.createSession(
                0,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                null,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE
        ));
        assertEquals("강의의 정원은 0명 이상이여야 해요 :(", exception.getMessage());
    }

    @Test
    @DisplayName("강의자가 입력되지 않았다면 예외를 던진다")
    void 강의자_입력이_안될경우() {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.createSession(
                1,
                null,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE
        ));
        assertEquals("강의자가 입력되질 않았어요 :(", exception.getMessage());
    }

    @Test
    @DisplayName("강의 타입이 입력안될 경우 예외를 던진다")
    void 강의_타입이_입력이_안될경우() {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                null
        ));
        assertEquals("강의 타입이 입력되질 않았어요 :(", exception.getMessage());
    }

    @Test
    @DisplayName("강의 상태가 입력 안될 경우 예외를 던진다")
    void 강의_상태가_입력이_안될경우() {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.of(
                1,
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now(),
                image,
                null,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE,
                1
        ));
        assertEquals("강의 상태가 입력되질 않았어요 :(", exception.getMessage());
    }

    @ParameterizedTest(name = "강의 상태에 모집중/모집 완료가 입력될 경우 예외를 던진다 - {index}. 제공된 강의 상태 : [{0}]")
    @DisplayName("강의 상태에 모집중/모집 완료가 입력될 경우 예외를 던진다")
    @EnumSource(value = SessionState.class, names = {"RECRUITING", "END_OF_RECRUITMENT"})
    void 강의_상태가_모집중_모집완료가_입력_인경우(SessionState sessionState) {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                sessionState,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE
        ));
        assertEquals("강의 상태는 모집 중/모집 종료를 입력하실수 없어요 :( 제공 된 강의 상태 [" + sessionState + "]", exception.getMessage());
    }

    @Test
    @DisplayName("모집 상태가 입력 안될 경우 예외를 던진다")
    void 모잡_상태가_입력이_안될경우() {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                null,
                SessionType.FREE
        ));
        assertEquals("모집 상태에 값이 입력 되지 않았어요 :(", exception.getMessage());
    }

    @ParameterizedTest(name = "모집 타입이 상태에 모집중/모집 완료가 아니라면 예외를 던진다 - {index}. 제공된 모집 상태 : [{0}]")
    @DisplayName("모집 타입이 상태에 모집중/모집 완료가 아니라면 예외를 던진다")
    @EnumSource(value = SessionState.class, names = {"PREPARING", "PROGRESSING", "FINISH"})
    void 모집_상태가_모집중_모집완료가_입력이_안될경우(SessionState recruitmentState) {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                recruitmentState,
                SessionType.FREE
        ));
        assertEquals("모집 상태는 모집중/모집 종료만 가능해요 :( 제공된 모집 상태 [" + recruitmentState + "]", exception.getMessage());
    }


    @ParameterizedTest(name = "수강 등록인원이 정원을 초과한다면 예외를 던진다 - {index}. 제공된 수강 인원 : [{0}] , 정원 : [0]")
    @DisplayName("수강 등록인원이 정원을 초과한다면 예외를 던진다")
    @ValueSource(ints = {10, 20, 30, 40})
    void 수강_등록인원이_정원을_초과할_경우(int numberOfStudentsRegistered) {

        Throwable exception = Assertions.assertThrows(IllegalStateException.class, () -> Session.of(
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
                numberOfStudentsRegistered
        ));
        assertEquals("등록인원이 정원을 초과 할 수 없어요 :( [정원 : 1명 ]", exception.getMessage());
    }

    @ParameterizedTest(name = "강의 시작 날짜 케이스별 테스트 - {index}. {3}")
    @DisplayName("강의 시작 날짜 케이스별 테스트")
    @MethodSource("validateDateArgument")
    void 강의_시작_날짜_케이스별_테스트(LocalDate startDate, LocalDate endDate, String expectedExceptionMessage, String testDisplayName) {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                startDate,
                endDate,
                image,
                SessionState.PREPARING,
                SessionState.RECRUITING,
                SessionType.FREE
        ));
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }


    static Stream<Arguments> validateDateArgument() {
        return Stream.of(
                Arguments.of(null, LocalDate.now(), "강의 시작일이 등록되질 않았어요 :(", "강의 시작일이 등록되지 않았다면 예외를 던진다"),
                Arguments.of(LocalDate.now(), null, "강의 종료일이 등록되질 않았어요 :(", "강의 종료일이 등록되지 않았다면 예외를 던진다"),
                Arguments.of(LocalDate.now().minusDays(1), LocalDate.now(), "강의 시작 날짜가 현재 날짜보다 앞일 수 없습니다 :(", "강의 시작 날짜가 현재 날짜 보다 앞이면 예외를 던진다"),
                Arguments.of(LocalDate.now(), LocalDate.now().minusDays(1), "강의 종료 날짜가 강의 시작 날짜보다 앞일 수 없습니다 :(", "강의 종료 날짜가 강의 시작 날짜 보다 앞이면 예외를 던진다")

        );
    }

}