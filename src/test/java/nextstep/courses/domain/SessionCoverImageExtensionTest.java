package nextstep.courses.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionCoverImageExtensionTest {

    @ParameterizedTest
    @ValueSource(strings = {"pdf", ".png", "1#2"})
    public void 지원하는_확장자가_아닌_경우_예외가_발생한다(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionCoverImageExtension.from(input));
    }
}
