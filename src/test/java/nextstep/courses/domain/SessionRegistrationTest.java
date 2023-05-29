package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionRegistrationTest {

    @DisplayName("수강신청 시 모집중이 아닌 경우 에러를 응답한다.")
    @ParameterizedTest
    @ValueSource(strings = {"PREPARE", "END"})
    public void 수강신청_모집중_이외_테스트(SessionStatusType statusType) {
        SessionRegistration sessionRegistration
                = new SessionRegistration(5, 0, statusType, new ArrayList<>());

        assertThatThrownBy(() -> sessionRegistration.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강신청 시 최대 인원을 초과하면 에러를 응답한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void 수강신청_최대_인원_테스트(int currentStudentCount) {
        int maxStudentCount = 2;
        List<NsUser> nsUsers = List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);

        SessionRegistration sessionRegistration
                = new SessionRegistration(maxStudentCount, currentStudentCount, SessionStatusType.RECRUITING, nsUsers);

        NsUser jaewon = new NsUser(3L, "jaewon0913", "password", "name", "jaewon@test.com");

        assertThatThrownBy(() -> sessionRegistration.register(jaewon))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상 수강 신청 시 올바른 수강 신청한 학생들을 전달한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void 수강신청_정상_테스트(int currentStudentCount) {
        int maxStudentCount = 5;

        List<NsUser> nsUsers = new ArrayList<>();
        for(int i = 0; i < currentStudentCount; i++) {
            nsUsers.add(new NsUser(i + 1L, "test" + i, "password", "name" + 1, i + "test@test.com"));
        }

        SessionRegistration sessionRegistration
                = new SessionRegistration(maxStudentCount, currentStudentCount, SessionStatusType.RECRUITING, nsUsers);

        sessionRegistration.register(NsUserTest.JAVAJIGI);
        sessionRegistration.register(NsUserTest.SANJIGI);

        assertThat(sessionRegistration.getNsUsers().size()).isEqualTo(currentStudentCount + 2);
    }
}
