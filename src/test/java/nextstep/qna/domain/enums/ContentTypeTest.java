package nextstep.qna.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentTypeTest {

    @DisplayName("ContentType Enum 객체가 잘 생성되는지 확인")
    @ParameterizedTest
    @EnumSource(ContentType.class)
    void 객체가_정상적으로_생성되는지_확인(ContentType contentType) {
        assertThat(contentType).isInstanceOf(ContentType.class);
    }

}
