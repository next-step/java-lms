package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class AnswersTest {
    @Test
    void 답글_리스트_객체생성() {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));
        assertThat(answers).isNotNull();
    }

    @Test
    void 답글_리스트_추가() {
        Answers answers = new Answers(new ArrayList<>());
        answers.add(AnswerTest.A1);
        assertThat(answers.getAnswers().size()).isEqualTo(1);
    }

    @Test
    void 답글_리스트_삭제() throws CannotDeleteException {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        answers.delete(deleteHistories, NsUserTest.JAVAJIGI);
        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @Test
    void 답글_리스트_삭제시_작성자_검증() throws CannotDeleteException {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        assertThatThrownBy(() -> answers.delete(deleteHistories, NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
