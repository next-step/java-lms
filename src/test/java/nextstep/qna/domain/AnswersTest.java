package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static nextstep.qna.domain.AnswerTest.*;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    @DisplayName("모든 답변의 작성자가 인자로 받은 로그인 사용자와 일치하면 모든 Answer의 삭제 상태를 true로 바꿔 답변을 삭제한다.")
    @Test
    void deleteAll() throws CannotDeleteException {
        // given
        Answer answer1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = new Answers(List.of(answer1, answer2));

        // when
        answers.deleteAll(JAVAJIGI);

        // then
        IntStream.range(0, 2)
            .forEach(i -> assertThat(answers.isDeleted(i)).isTrue());
    }

    @DisplayName("답변을 삭제할 때 인자로 로그인 사용자를 받아 모든 답변의 작성자와 일치하지 않으면 예외를 반환한다.")
    @Test
    void test() {
        // given
        Answers answers = new Answers(List.of(A1, A2));

        // when & then
        assertThatThrownBy(() -> answers.deleteAll(JAVAJIGI)).isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
