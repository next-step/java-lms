package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionRegistrationTest {

    @DisplayName("수강신청 시 모집중이 아닌 경우 에러를 응답한다.")
    @ParameterizedTest
    @ValueSource(strings = {"PREPARE", "END"})
    public void 수강신청_모집중_이외_테스트(SessionStatusType statusType) {
        SessionRegistration sessionRegistration = new SessionRegistration(5, 1, statusType);

        assertThatThrownBy(() -> sessionRegistration.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강신청 시 최대 인원을 초과하면 에러를 응답한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void 수강신청_최대_인원_테스트(int currentStudentCount) {
        int maxStudentCount = 2;

        SessionRegistration sessionRegistration = new SessionRegistration(maxStudentCount, currentStudentCount, SessionStatusType.RECRUITING);

        assertThatThrownBy(() -> sessionRegistration.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
