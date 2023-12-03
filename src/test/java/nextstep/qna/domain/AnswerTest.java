package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @ParameterizedTest
    @DisplayName("질문자와 답변자가 다르면 예외를 던진다.")
    @MethodSource("parametersProvider")
    void is_not_owner(NsUser given, boolean expected) {
        // when
        boolean result = A1.isOwner(given);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> parametersProvider() {
        return Stream.of(
                arguments(JAVAJIGI, true),
                arguments(SANJIGI, false)
        );
    }

    @Test
    @DisplayName("질문이 삭제되면 아래 댓글들도 삭제된다.")
    void delete() {
        // given
        Question question = new Question(JAVAJIGI, "질문1", "질문1입니다");
        Answer answer = new Answer(JAVAJIGI, question, "답글2입니다");
        question.addAnswer(answer);

        // when
        answer.writeDeleteAnswerHistory();

        // then
        assertThat(answer.isDeleted()).isTrue();
    }
}
