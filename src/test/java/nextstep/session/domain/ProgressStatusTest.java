package nextstep.session.domain;

import nextstep.session.NotFoundStatusException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProgressStatusTest {

    @ParameterizedTest
    @MethodSource("provideStringAndEnum")
    void 상태에_따른_객체_반환(String input, ProgressStatus status) throws NotFoundStatusException {

        // when
        ProgressStatus result = ProgressStatus.of(input);

        // then
        Assertions.assertThat(result).isEqualTo(status);
    }

    @Test
    void 상태에_따른_객체가_존재하지_않으면_예외_발생() {

        assertThatThrownBy(
                () -> ProgressStatus.of("none"))
                .isInstanceOf(NotFoundStatusException.class);
    }

    private static Stream<Arguments> provideStringAndEnum() {
        return Stream.of(
                Arguments.of("ready", ProgressStatus.READY),
                Arguments.of("recruiting", ProgressStatus.RECRUITING),
                Arguments.of("end", ProgressStatus.END)
        );
    }
}
