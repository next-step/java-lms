package nextstep.qna.domain.vo;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDetailTest {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(NsUserTest.JAVAJIGI, true),
                Arguments.of(NsUserTest.SANJIGI, false)
        );
    }

    @DisplayName("로그인 사용자와 질문한 사람이 동일한 사람인지 확인")
    @ParameterizedTest
    @MethodSource("dataProvider")
    void delete_로그인_사용자와_질문한_사람이_동일한지_여부(NsUser loginUser, boolean expected) {
        QuestionDetail detail = QuestionDetail.of("제목1", "내용1", NsUserTest.JAVAJIGI);
        assertThat(detail.isOwner(loginUser)).isEqualTo(expected);
    }

}
