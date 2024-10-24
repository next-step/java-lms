package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.QuestionTest.Q1;

public class DeleteHistoryTest {
    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
    }

    @Test
    void create() {
        DeleteHistory actualQuestionHistory = new DeleteHistory(Q1);
        DeleteHistory expectedQuestionHistory = new DeleteHistory(question);

        DeleteHistory actualAnswerHistory = new DeleteHistory(A1);
        DeleteHistory expectedAnswerHistory = new DeleteHistory(answer);

        Assertions.assertThat(actualQuestionHistory).isEqualTo(expectedQuestionHistory);
        Assertions.assertThat(actualAnswerHistory).isEqualTo(expectedAnswerHistory);
    }
}