package nextstep.courses.domain.status;

import nextstep.courses.exception.SessionRecruitmentStatusInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static nextstep.courses.domain.status.RecruitmentStatus.NOT_RECRUITMENT;
import static nextstep.courses.domain.status.RecruitmentStatus.RECRUITMENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionRecruitmentStatusTest {

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("statusFixture")
    @DisplayName("[성공] 문자열을 모집 상태 객체로 변환한다.")
    void 모집_상태_변환(String statusString, RecruitmentStatus status) {
        assertThat(RecruitmentStatus.convert(statusString)).isEqualTo(status);
    }

    @Test
    @DisplayName("[실패] 모집 상태에 없는 문자열을 객체로 변환할 경우 SessionRecruitmentStatusInvalidException 예외가 발생한다.")
    void 모집_상태가_아닌_문자열_변환() {
        assertThatExceptionOfType(SessionRecruitmentStatusInvalidException.class)
                .isThrownBy(() -> {
                    RecruitmentStatus.convert("모집 상태에 없는 문자열");
                });
    }

    static Stream<Arguments> statusFixture() {
        return Stream.of(
                Arguments.of("모집중", RECRUITMENT),
                Arguments.of("비모집중", NOT_RECRUITMENT)
        );
    }

}
