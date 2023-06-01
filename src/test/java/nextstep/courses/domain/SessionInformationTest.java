package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionInformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("세션 정보 객체 테스트")
class SessionInformationTest {

    @ParameterizedTest(name = "기수는 양수가 아니면 예외가 발생한다")
    @ValueSource(ints = {0, -9})
    void termException(int term) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionInformation("TDD Clean Code", term))
                .withMessage("the term is must be greater than zero");
    }

    @DisplayName("세션 타이틀을 얻을 수 있다")
    @Test
    void sessionTitle(){
        SessionInformation sessionInformation = new SessionInformation("TDD Clean Code", 16);
        Assertions.assertThat(sessionInformation.fetchTitle()).isEqualTo("TDD Clean Code 16기");
    }
}