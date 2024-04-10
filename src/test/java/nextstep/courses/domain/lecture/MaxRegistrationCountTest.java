package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.error.exception.MaxRegistrationCountNotZero;
import org.junit.jupiter.api.Test;

class MaxRegistrationCountTest {

    @Test
    public void 강의_최대_수강_인원은_0일_경우_예외가_발생한다(){
        assertThatThrownBy(() -> new MaxRegistrationCount(new RegistrationCount(0)))
            .isInstanceOf(MaxRegistrationCountNotZero.class)
            .hasMessage("최대 등록수는 0일수 없습니다 입력값: 0");
    }
}
