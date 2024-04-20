package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionProgressStatusInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static nextstep.courses.domain.session.ProgressStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionProgressStatusTest {

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("statusFixture")
    @DisplayName("[성공] 문자열을 진행 상태 객체로 변환한다.")
    void 진행_상태_변환(String statusString, ProgressStatus status) {
        assertThat(ProgressStatus.convert(statusString)).isEqualTo(status);
    }

    @Test
    @DisplayName("[실패] 진행 상태에 없는 문자열을 객체로 변환할 경우 SessionProgressStatusInvalidException 예외가 발생한다.")
    void 진행_상태가_아닌_문자열_변환() {
        assertThatExceptionOfType(SessionProgressStatusInvalidException.class)
                .isThrownBy(() -> {
                    ProgressStatus.convert("진행 상태에 없는 문자열");
                });
    }

    static Stream<Arguments> statusFixture() {
        return Stream.of(
                Arguments.of("준비중", PREPARING),
                Arguments.of("진행중", ACTIVE),
                Arguments.of("종료", FINISHED)
        );
    }

}
