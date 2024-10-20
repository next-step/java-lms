package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @DisplayName("답변 리스트를 가지는 Answers를 만들 수 있다.")
    @Test
    void create() {
        Answers result = new Answers(List.of(A1, A2));

        assertThat(result).isEqualTo(new Answers(List.of(A1, A2)));
    }

    @DisplayName("답변 리스트에 답변을 추가할 수 있다.")
    @Test
    void add() {
        Answers answer = new Answers();

        answer.add(A1);

        assertThat(answer).isEqualTo(new Answers(List.of(A1)));
    }

    @DisplayName("답변 리스트의 권한과 입력한 유저가 같지 않으면 예외를 발생한다.")
    @Test
    void validateOwner() {
        Answers answers = new Answers(List.of(A1, A2));

        assertThatThrownBy(() -> answers.validateOnwer(NsUserTest.GREEN))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 리스트를 삭제한다.")
    @Test
    void deleteAll() {
        Answers answers = new Answers(List.of(A1, A2));

        List<Answer> result = answers.deleteAll();

        assertThat(result)
                .extracting(Answer::getId, Answer::isDeleted)
                .containsExactly(
                        Tuple.tuple(A1.getId(), true),
                        Tuple.tuple(A2.getId(), true)
                );
    }
}
