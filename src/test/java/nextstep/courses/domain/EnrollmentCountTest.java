package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentCountTest {

    @ParameterizedTest
    @DisplayName("강의 좌석 수가 0이면 더 이상 수강할 수 없다")
    @CsvSource(value = {"0:false", "1:true"}, delimiter = ':')
    void paid_lecture_cannot_exceed_limit_number(Integer count, boolean result) {
        assertThat(new EnrollmentCount(count).hasRemainingCount()).isEqualTo(result);
    }

    @Test
    @DisplayName("수강인원은 0이상")
    void if_negative_number() {
        assertThatThrownBy(() -> {
            new EnrollmentCount(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 시에 강의 수강 인원 수 감소")
    void decrease_count_when_enroll() {
        final int originalCount = 100;
        final int resultCount = 99;
        EnrollmentCount enrollmentCount = new EnrollmentCount(originalCount).decreaseCount();
        assertThat(enrollmentCount).isEqualTo(new EnrollmentCount(resultCount));
    }
}
