package nextstep.courses.domian;

import nextstep.courses.domain.SessionStatusType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTypeTest {

    @ParameterizedTest(name = "강의 상태가 {0}라면 수강신청 가능상태는 {1} 이다.")
    @CsvSource(value = {"READY, false", "RECRUITMENT, true", "END, false"})
    void isNotRecruitment(SessionStatusType sessionStatusType, boolean expected) {
        assertThat(sessionStatusType.isRecruitment()).isEqualTo(expected);
    }
}
