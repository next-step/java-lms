package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SessionParticipantsTest {

    @ParameterizedTest
    @DisplayName("수강생들이 다 찼는지 확인한다.")
    @CsvSource(value = {"4, false", "3, true"})
    void check_session_is_full(long given, boolean expected) {
        // given
        SessionParticipants sessionParticipants = new SessionParticipants(
                List.of(new NsUser(1L, "1", "1234", "이수찬1", "email"),
                        new NsUser(2L, "2", "1234", "이수찬2", "email"),
                        new NsUser(3L, "3", "1234", "이수찬3", "email")
                        ), given
        );

        // when
        boolean result = sessionParticipants.isFull();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
