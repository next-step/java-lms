package nextstep.courses.domain.lecture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.text.MessageFormat;
import nextstep.courses.error.exception.LectureNameEmptyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class LectureNameTest {

    @Test
    void 강의이름은_문자열을_통해_생성되어야_합니다(){
        LectureName lectureName = new LectureName("선형대수학");

        assertThat(lectureName.getValue()).isEqualTo("선형대수학");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 강의이름은_NULL_또는_빈문자열이_아니어야_합니다(String input){
        assertThatThrownBy(() -> new LectureName(input))
            .isInstanceOf(LectureNameEmptyException.class)
            .hasMessage(MessageFormat.format("강의 이름이 입력되지 않았습니다 입력값: {0}", input));
    }
}
