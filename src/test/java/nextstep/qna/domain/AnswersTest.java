package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @ParameterizedTest(name = "답변의 주인이 아닌 유저가 삭제하는 경우 예외가 발생하는 테스트.")
    @MethodSource("provideNotMatchUserAndAnswer")
    void testDelete_CannotDeleteException_예외발생(NsUser loginUser, Answers answers) {
        assertThatThrownBy(() -> answers.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    static Stream<Arguments> provideNotMatchUserAndAnswer() {
        return Stream.of(
                Arguments.arguments(JAVAJIGI, new Answers(List.of(A2))),
                Arguments.arguments(SANJIGI, new Answers(List.of(A1)))
        );
    }
}
