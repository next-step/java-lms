package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @ParameterizedTest(name = "질문의 주인이 아닌 유저가 삭제하는 경우 예외가 발생하는 테스트.")
    @MethodSource("provideNotMatchUserAndQuestion")
    void testDelete_CannotDeleteException_예외발생(NsUser loginUser, Question question) {
        assertThatThrownBy(() -> question.delete(loginUser, 1L))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    static Stream<Arguments> provideNotMatchUserAndQuestion() {
        return Stream.of(
                Arguments.arguments(JAVAJIGI, new Question(SANJIGI, "title", "contents")),
                Arguments.arguments(SANJIGI, new Question(JAVAJIGI, "title", "contents"))
        );
    }

    @ParameterizedTest(name = "질문의 주인인 유저가 삭제하는 경우 deleted 필드 true 변경되는지 확인하는 테스트")
    @MethodSource("provideMatchUserAndQuestion")
    void testDelete_삭제_확인_테스트(NsUser loginUser, Question question) throws CannotDeleteException {
        assertThat(question.isDeleted()).isFalse();
        question.delete(loginUser, 0L);
        assertThat(question.isDeleted()).isTrue();
    }

    static Stream<Arguments> provideMatchUserAndQuestion() {
        return Stream.of(
                Arguments.arguments(JAVAJIGI, new Question(JAVAJIGI, "title", "contents")),
                Arguments.arguments(SANJIGI, new Question(SANJIGI, "title", "contents"))
        );
    }
}
