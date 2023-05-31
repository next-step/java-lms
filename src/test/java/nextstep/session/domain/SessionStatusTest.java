package nextstep.session.domain;

import nextstep.session.NotFoundStatusException;
import nextstep.session.NotRecruitException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class SessionStatusTest {

    @ParameterizedTest
    @MethodSource("provideStringAndEnum")
    void 상태에_따른_객체_반환(String input, SessionStatus status) throws NotFoundStatusException {

        // when
        SessionStatus result = SessionStatus.of(input);

        // then
        Assertions.assertThat(result).isEqualTo(status);
    }

    @Test
    void 상태에_따른_객체가_존재하지_않으면_예외_발생() {

        assertThatThrownBy(
                () -> SessionStatus.of("none"))
                .isInstanceOf(NotFoundStatusException.class);
    }

    private static Stream<Arguments> provideStringAndEnum() {
        return Stream.of(
                Arguments.of("ready", SessionStatus.READY),
                Arguments.of("recruiting", SessionStatus.RECRUITING),
                Arguments.of("end", SessionStatus.END)
        );
    }
}
