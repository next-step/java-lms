package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParserTest {
    @DisplayName("파일명 포맷이 아닐경우 예외 발생")
    @Test
    void 파일명_포맷_확인_예외발생() {
        assertThatThrownBy(() -> Parser.fileNameAndExtensionParsing("next.step.java"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Parser.fileNameAndExtensionParsing("nextstep"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파일명과 확장자 리스트 리턴")
    @Test
    void 파일명_확장자_리턴() {
        assertThat(Parser.fileNameAndExtensionParsing("nextstep.png")).containsExactly("nextstep", "png");
        assertThat(Parser.fileNameAndExtensionParsing("nextstep.jpeg")).containsExactly("nextstep", "jpeg");
        assertThat(Parser.fileNameAndExtensionParsing("nextstep.gif")).containsExactly("nextstep", "gif");
    }
}