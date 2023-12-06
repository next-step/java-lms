package nextstep.session;

import nextstep.session.domain.SessionStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {

    @ParameterizedTest
    @CsvSource(value = {"PREPARING", "RECRUITING", "END"})
    void 강의_상태는_준비중_모집중_종료_3가지_상태를_가진다(String status){
        List<SessionStatus> sessionStatuses = Arrays.stream(SessionStatus.values()).collect(Collectors.toList());

        assertThat(sessionStatuses).contains(SessionStatus.valueOf(status));
    }
}
