package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageFileInfoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

public class NameTest {

    @DisplayName("파일명이 비어있거나 null이면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateEmpty(String fileName) {
        assertThatThrownBy(() -> new Name(fileName)).isInstanceOf(ImageFileInfoException.class)
            .hasMessage("파일 이름이 존재하지 않습니다.");
    }

    @DisplayName("파일명은 존재하지만 확장자가 존재하지 않으면 예외를 발생시킨다.")
    @Test
    void validateExtension() {
        assertThatThrownBy(() -> new Name("fileName")).isInstanceOf(ImageFileInfoException.class)
            .hasMessage("확장자가 존재하지 않습니다");
    }
}
