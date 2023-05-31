package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    private List<Answer> answerExamples;

    @BeforeEach
    public void setup() {
        answerExamples = new ArrayList<>();
    }

    @Test
    void deleteAll() throws Exception {
        Answer A = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answerExamples.add(A);
        Answers answers = new Answers(answerExamples);

        List<DeleteHistory> deleteHistories = answers.deleteAll(NsUserTest.JAVAJIGI);
        DeleteHistory answerDeleteHistory = new DeleteHistory(ContentType.ANSWER, A.getId(), A.getWriter(), LocalDateTime.now());
        assertThat(deleteHistories).containsExactly(answerDeleteHistory);
    }

    @Test
    void deleteAll_다른_작성자_답변이_하나라도_있다면_CannotDeleteException() throws Exception {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answerExamples.add(A1);
        answerExamples.add(A2);
        Answers answers = new Answers(answerExamples);

        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
