package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileNameStructureTest {
    public static FileNameStructure NORMAL_FILE_NAME = new FileNameStructure("step", "png");

    @DisplayName("파일 확장자가 지원하지 않는 확장자라면 예외 발생")
    @ParameterizedTest
    //@ValueSource(strings = {"java.step", "next.step"})
    @CsvSource(value = {"java:step", "next:step"}, delimiter = ':')
    void 확장자_확인(String name, String extension) {
        assertThatThrownBy(() -> new FileNameStructure(name, extension))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 확장자입니다.");
    }
}