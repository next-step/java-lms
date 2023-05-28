package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @DisplayName("강의번호 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:false", "3:false"}, delimiterString = ":")
    public void 강의번호_테스트(long id, boolean isSession) {
        Session session = new Session(1L, null, null, null, null, null, null);

        assertThat(session.isSession(id)).isEqualTo(isSession);
    }
}
