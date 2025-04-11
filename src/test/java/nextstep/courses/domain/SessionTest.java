package nextstep.courses.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionTest {

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"READY", "CLOSED"})
    public void 수강신청_시_강의상태가_모집중_상태가_아닌_경우_예외_발생(SessionStatus input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Session.checkRecruiting(input));
    }
}
