package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ParserTest {
    @DisplayName("파일명 포맷이 아닐경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"next.step.java", "nextstep"})
    void 파일명_포맷_확인_예외발생(String name) {
        assertThatThrownBy(() -> Parser.fileNameFormatChecking(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일명이 올바르지않습니다.");
    }
}