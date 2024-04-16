package nextstep.courses.domain.student;

import nextstep.courses.exception.StudentEnrollmentStatusInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static nextstep.courses.domain.student.StudentEnrollmentStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StudentEnrollmentStatusTest {

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("statusFixture")
    @DisplayName("[성공] 문자열을 c 객체로 변환한다.")
    void 수강생_신청_상태_변환(String statusString, StudentEnrollmentStatus status) {
        assertThat(StudentEnrollmentStatus.convert(statusString)).isEqualTo(status);
    }

    @Test
    @DisplayName("[실패] 수강생 신청 상태에 없는 문자열을 객체로 변환할 경우 StudentEnrollmentStatusInvalidException 예외가 발생한다.")
    void 수강생_신청_상태가_아닌_문자열_변환() {
        assertThatExceptionOfType(StudentEnrollmentStatusInvalidException.class)
                .isThrownBy(() -> {
                    StudentEnrollmentStatus.convert("수강생 신청 상태에 없는 문자열");
                });
    }

    static Stream<Arguments> statusFixture() {
        return Stream.of(
                Arguments.of("대기중", PENDING),
                Arguments.of("승인", APPROVAL),
                Arguments.of("취소", CANCEL)
        );
    }

}
