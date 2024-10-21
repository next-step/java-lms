package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

class AnswersTest {

    @Test
    void 생성() {
        List<Answer> answerList = List.of(
            new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "test"),
            new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "test")
        );
        Answers answers = new Answers(answerList);

        assertThat(answers).isEqualTo(new Answers(answerList));
    }

    @Test
    void 답변들을_삭제한다() {
        List<Answer> answerList = List.of(
            new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "test comment1"),
            new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "test comment2")
        );
        Answers answers = new Answers(answerList);
        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).hasSize(2);
    }

    @Test
    void 답변을_추가한다() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);

        assertThat(answers).isEqualTo(new Answers(List.of(AnswerTest.A1)));
    }
}
