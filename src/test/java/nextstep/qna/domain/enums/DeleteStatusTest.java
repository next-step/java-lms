package nextstep.qna.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteStatusTest {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(DeleteStatus.YES, true),
                Arguments.of(DeleteStatus.NO, false)
        );
    }

    @DisplayName("DeleteStatus Enum 객체가 잘 생성되는지 확인")
    @ParameterizedTest
    @EnumSource(DeleteStatus.class)
    void 객체가_정상적으로_생성되는지_확인(DeleteStatus deleteStatus) {
        assertThat(deleteStatus).isInstanceOf(DeleteStatus.class);
    }

}
