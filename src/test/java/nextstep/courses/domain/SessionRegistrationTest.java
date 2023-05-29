package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionRegistrationTest {

    @DisplayName("수강신청 시 모집중이 아닌 경우 에러를 응답한다.")
    @ParameterizedTest
    @ValueSource(strings = {"PREPARE", "END"})
    public void 수강신청_모집중_이외_테스트(SessionStatusType statusType) {
        SessionRegistration sessionRegistration = new SessionRegistration(5, 1, statusType);

        assertThatThrownBy(() -> sessionRegistration.register(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강신청 시 최대 인원을 초과하면 에러를 응답한다.")
    @ParameterizedTest
    @CsvSource(value = {"2:4", "3:4", "4:4"}, delimiterString = ":")
    public void 수강신청_최대_인원_테스트(int currentStudentCount, int registerStudentCount) {
        int maxStudentCount = 5;

        SessionRegistration sessionRegistration = new SessionRegistration(maxStudentCount, currentStudentCount, SessionStatusType.RECRUITING);

        assertThatThrownBy(() -> sessionRegistration.register(registerStudentCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
