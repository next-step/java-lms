package nextstep.courses.domian;

import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionStatusType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {

    @ParameterizedTest(name = "강의 상태가 {0}라면 수강신청 가능상태는 {1} 이다.")
    @CsvSource(value = {"READY, true", "ONGOING, true", "END, false"})
    void createSessionStatus_기본모집중(SessionStatusType input, boolean expected) {
        SessionStatus sessionStatus = new SessionStatus(input);

        boolean actual = sessionStatus.isRecruitment();

        assertThat(actual).isEqualTo(expected);
    }
}
