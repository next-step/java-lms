package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    @DisplayName("Answers 생성")
    void create() {
        assertThat(new Answers(List.of(A1))).isInstanceOf(Answers.class);
    }

    @Test
    @DisplayName("Answer 추가")
    void add() {
        List<Answer> answerList = new ArrayList<>(List.of(A1));

        Answers answers = new Answers(answerList);

        answers.add(A2);

        assertThat(answerList).contains(A2);
    }

    @Test
    @DisplayName("전체 삭제")
    void deleteAll() throws CannotDeleteException {
        List<Answer> answerList = new ArrayList<>(List.of(A1));

        Answers answers = new Answers(answerList);

        answers.deleteAll(JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }
}
