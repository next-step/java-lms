package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {
    @Test
    void add_answer() {
        // given
        Answers answers = new Answers();

        // when
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        // then
        assertThat(answers).isEqualTo(new Answers(AnswerTest.A1, AnswerTest.A2));
    }

    @Test
    void delete() {
        // given
        Answers answers = new Answers(AnswerTest.A1, AnswerTest.A2);

        // when
        answers.delete();

        // then
        Optional<Answer> notDeletedAnswer = answers.answers().stream()
                .filter(answer -> !answer.isDeleted())
                .findAny();
        assertThat(notDeletedAnswer).isEmpty();
    }

    @ParameterizedTest(name = "[{index}/2] {displayName}")
    @MethodSource("loginUserAndContainsNotOwnedAnswer")
    @DisplayName("로그인 유저 외 답변을 한 유저가 있는지 여부 조회")
    void contains_not_owned_answer(NsUser loginUser, boolean containsNotOwnedAnswer) {
        // given
        Answers answers = new Answers(AnswerTest.A1);

        // when, then
        assertThat(answers.containsNotOwnedAnswer(loginUser)).isEqualTo(containsNotOwnedAnswer);
    }

    static Stream<Arguments> loginUserAndContainsNotOwnedAnswer() {
        return Stream.of(
                Arguments.arguments(NsUserTest.JAVAJIGI, false),
                Arguments.arguments(NsUserTest.SANJIGI, true)
        );
    }
}
