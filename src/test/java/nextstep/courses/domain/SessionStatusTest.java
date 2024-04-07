package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.exception.CannotEnrollmentSessionStatusException;
import nextstep.courses.exception.SessionExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionStatusTest {

    @ParameterizedTest
    @MethodSource("sessionStatusFixture")
    @DisplayName("[성공] 강의 상태에 따른 모집 가능 여부를 확인한다.")
    void 강의_상태_모집_가능_여부(SessionStatus status, CannotEnrollmentSessionStatusException exception) {
        if (exception == null) {
            assertThatNoException().isThrownBy(status::validateCanEnrollment);
            return;
        }
        assertThatExceptionOfType(exception.getClass())
                .isThrownBy(status::validateCanEnrollment)
                .withMessageContaining(SessionExceptionMessage.CANNOT_ENROLLMENT_SESSION_STATUS.getMessage());
    }

    static Stream<Arguments> sessionStatusFixture() {
        return Stream.of(
                Arguments.of(SessionStatus.PREPARING, new CannotEnrollmentSessionStatusException(SessionStatus.PREPARING)),
                Arguments.of(SessionStatus.RECRUITING, null),
                Arguments.of(SessionStatus.FINISHED, new CannotEnrollmentSessionStatusException(SessionStatus.FINISHED))
        );
    }
}
