package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nextstep.qna.domain.AnswerFixture.JAVAJIGI_답변_복수_개;
import static nextstep.qna.domain.AnswerFixture.SANJIGI_답변_복수_개;
import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @ParameterizedTest(name = "답변의 주인이 아닌 유저가 삭제하는 경우 예외가 발생하는 테스트.")
    @MethodSource("provideNotMatchUserAndAnswer")
    void testDelete_CannotDeleteException_예외발생(NsUser loginUser, Answers answers) {
        assertThatThrownBy(() -> answers.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Answers.NOT_OWNER_MESSAGE);
    }

    static Stream<Arguments> provideNotMatchUserAndAnswer() {
        return Stream.of(
                Arguments.arguments(JAVAJIGI, new Answers(List.of(A2))),
                Arguments.arguments(SANJIGI, new Answers(List.of(A1)))
        );
    }

    @ParameterizedTest(name = "답변들이 정상적으로 삭제되는지 확인하는 테스트")
    @MethodSource("provideUserAndAnswer")
    void testDelete_삭제_테스트(NsUser nsUser, Answers answers) throws CannotDeleteException {
        answers.getAnswers()
                .forEach(answer -> assertThat(answer.isDeleted()).isFalse());

        answers.delete(nsUser);

        answers.getAnswers()
                .forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    static Stream<Arguments> provideUserAndAnswer() {
        return Stream.of(
                Arguments.arguments(JAVAJIGI, JAVAJIGI_답변_복수_개()),
                Arguments.arguments(SANJIGI, SANJIGI_답변_복수_개())
        );
    }
}
