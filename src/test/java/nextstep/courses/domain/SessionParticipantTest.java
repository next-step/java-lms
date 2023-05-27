package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SessionParticipantTest {

    @ParameterizedTest
    @CsvSource(value = {"31, true", "29, false"})
    void 강의_수강인원_테스트(int students, boolean result) {
        int period = 1;
        SessionParticipant sessionParticipant = new SessionParticipant(period, students);

        Assertions.assertThat(sessionParticipant.isFullSession()).isEqualTo(result);
    }

    @Test
    void 강의_수강인원_증가_테스트() {
        int period = 1;
        int students = 20;

        SessionParticipant sessionParticipant = new SessionParticipant(period, students);
        sessionParticipant.participateStudent();

        Assertions.assertThat(sessionParticipant.getStudents()).isEqualTo(++students);
    }
}
