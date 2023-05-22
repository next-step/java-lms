package nextstep.courses.domain;

import nextstep.courses.CannotEnrollStatusException;
import nextstep.courses.NotIncludePeriodException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static nextstep.courses.domain.SessionPeriodFixture.과거기간;
import static nextstep.courses.domain.SessionPeriodFixture.미래기간;
import static nextstep.courses.domain.SessionPeriodFixture.현재기간_포함;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @ParameterizedTest(name = "현재 수강신청 가능기간이면서 강의 등록 가능한 상태인 경우 예외를 발생하는지 테스트")
    @MethodSource("provideCanEnrollSession")
    void testEnableEnrollment_등록_가능(Session session) {
        session.enrollSession();
        assertThat(session.getNumberOfStudent()).isEqualTo(1);
        session.enrollSession();
        assertThat(session.getNumberOfStudent()).isEqualTo(2);
    }

    static Stream<Arguments> provideCanEnrollSession() {
        return Stream.of(
                Arguments.arguments(new Session(현재기간_포함(), SessionPaymentType.FREE, SessionStatus.RECRUITING))
        );
    }

    @ParameterizedTest(name = "현재 수강신청 가능기간이 아니면서 강의 등록이 가능한 상태인 경우 예외를 발생하는지 테스트")
    @MethodSource("provideNotMatchedPeriodSession")
    void testEnableEnrollment_수강신청_기간_예외(Session session) {
        assertThatThrownBy(() -> session.enrollSession())
                .isInstanceOf(NotIncludePeriodException.class)
                .hasMessage(NotIncludePeriodException.MESSAGE);
    }

    static Stream<Arguments> provideNotMatchedPeriodSession() {
        return Stream.of(
                Arguments.arguments(new Session(과거기간(), SessionPaymentType.FREE, SessionStatus.RECRUITING)),
                Arguments.arguments(new Session(미래기간(), SessionPaymentType.FREE, SessionStatus.RECRUITING))
        );
    }

    @ParameterizedTest(name = "현재 수강신청 가능기간이면서 강의 등록 불가능한 상태인 경우 예외를 발생하는지 테스트")
    @MethodSource("provideCannotEnrollSession")
    void testEnableEnrollment_등록_불가_상태_예외(Session session) {
        assertThatThrownBy(() -> session.enrollSession())
                .isInstanceOf(CannotEnrollStatusException.class)
                .hasMessage(CannotEnrollStatusException.MESSAGE);
    }

    static Stream<Arguments> provideCannotEnrollSession() {
        return Stream.of(
                Arguments.arguments(new Session(현재기간_포함(), SessionPaymentType.FREE, SessionStatus.END)),
                Arguments.arguments(new Session(현재기간_포함(), SessionPaymentType.FREE, SessionStatus.READY))
        );
    }
}
