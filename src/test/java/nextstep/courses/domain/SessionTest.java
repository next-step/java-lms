package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("Session 생성")
    void testCreate() {
        // given
        final var session = SessionBuilder.builder();
        Assertions.assertThat(session).isNotNull();
    }

    @Test
    @DisplayName("강의 생성시 초기 상태는 NOT_STARTED 이다")
    void testCreateStatus() {
        // given
        Session session = SessionBuilder.builder()
                .withEnrollmentContext(SessionEnrollmentContextBuilder.builder())
                .build();

        // when&then
        Assertions.assertThat(session.statusEquals(SessionEnrollmentContext.SessionStatus.NOT_STARTED)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("강의 가격과 BillType이 일치하지 않으면 예외가 발생한다")
    @MethodSource("provideBillTypeAndPriceFail")
    void testCreateBillType(Session.BillType billType, Price price) {
        assertThatThrownBy(() -> SessionBuilder.builder()
                .withBillType(billType)
                .withPrice(price)
                .build()
        ).isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("BillType과 Price가 일치하지 않습니다.");
    }

    @ParameterizedTest
    @DisplayName("강의 가격과 BillType이 일치하면 예외가 발생하지 않는다")
    @MethodSource("provideBillTypeAndPriceSuccess")
    void testCreateBillTypeSuccess(Session.BillType billType, Price price) {
        assertThatCode(() -> SessionBuilder.builder()
                .withBillType(billType)
                .withPrice(price)
                .build()
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @EnumSource(value = SessionEnrollmentContext.SessionStatus.class, names = {"IN_PROGRESS", "FINISHED"})
    @DisplayName("수강신청은 모집상태가 IN_PROGRESS 일때만 가능하다")
    void testEnrollStatus(SessionEnrollmentContext.SessionStatus sessionStatus) {
        // given
        Session session = SessionBuilder.builder()
                .withEnrollmentContext(SessionEnrollmentContextBuilder.builder()
                        .withProgressStatus(sessionStatus))
                .build();

        // when&then
        Assertions.assertThat(session.isEnrollable()).isFalse();
    }

    @Test
    @DisplayName("수강신청은 최대인원수를 초과할 수 없다")
    void testEnrollMax() {
        // given
        Session session = SessionBuilder
                .builder()
                .withEnrollmentContext(
                        SessionEnrollmentContextBuilder
                                .builder()
                                .withMaxEnrollment(1L)
                )
                .build();

        session.start();

        // when&then
        Assertions.assertThat(session.isEnrollable()).isTrue();
        session.enroll(NsUserTest.JAVAJIGI);
        Assertions.assertThat(session.isEnrollable()).isFalse();
    }

    static Stream<Arguments> provideBillTypeAndPriceFail() {
        return Stream.of(
                Arguments.of(Session.BillType.FREE, new Price(1110L)),
                Arguments.of(Session.BillType.PAID, new Price(0L))
        );
    }

    static Stream<Arguments> provideBillTypeAndPriceSuccess() {
        return Stream.of(
                Arguments.of(Session.BillType.FREE, new Price(0L)),
                Arguments.of(Session.BillType.PAID, new Price(2220L))
        );
    }
}
