package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

class ParserTest {
    @DisplayName("파일명이 없을경우 예외발생")
    @ParameterizedTest
    @NullAndEmptySource
    void 파일명_null(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> Parser.getBaseName(name))
                .withMessage("파일명이 올바르지않습니다.");
    }

    @DisplayName("정상 파일명")
    @ParameterizedTest
    @CsvSource(value = {"next.png:next", "choi.jae.hyeon.svg:choi.jae.hyeon"}, delimiter = ':')
    void 정상_파일명(String fileName, String expectedFileName) {
        assertThat(Parser.getBaseName(fileName)).isEqualTo(expectedFileName);
    }

    @DisplayName("확장자가 없을경우 예외발생")
    @Test
    void 파일명_null() {
        assertThatIllegalArgumentException().isThrownBy(() -> Parser.getExtension("nextstep"))
                .withMessage("확장자가 없습니다.");
    }

    @DisplayName("정상 확장자")
    @ParameterizedTest
    @CsvSource(value = {"next.png:png", "choi.jae.hyeon.svg:svg"}, delimiter = ':')
    void 정상_확장자(String fileName, String expectedExtension) {
        assertThat(Parser.getExtension(fileName)).isEqualTo(expectedExtension);
    }
}