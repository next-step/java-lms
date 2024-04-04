package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @DisplayName("width 와 height는 각각 300, 200 픽셀 이상이여야 한다.")
    @Test
    void minimumHeightAndWidth() {
        assertThatCode(() -> new Image(1000, 200, 300, ""))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> new Image(1000, 199, 300, ""))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Image(1000, 200, 299, ""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
