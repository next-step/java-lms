package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    @DisplayName("Answers 삭제 테스트")
    void testDeleteAnswers() throws CannotDeleteException {
        int answerCount = 10;
        NsUser writer = NsUserTest.JAVAJIGI;
        Answers answers = makeJavajigiAnswers(writer, answerCount);
        List<DeleteHistory> deleteHistories = answers.deleteAnswers(writer);
        List<Answer> deletedAnswers = answers.getAnswers();

        assertThat(deleteHistories).hasSize(answerCount);
        assertThat(deletedAnswers).hasSize(answerCount);
        for (int i = 0; i < answerCount; i++) {
            assertThat(deletedAnswers.get(i).isDeleted()).isTrue();

            assertThat(deleteHistories.get(i).toString())
                    .contains(writer.toString());
        }
    }

    private Answers makeJavajigiAnswers(NsUser writer, int count) {
        Answers answers = new Answers();

        for (int i = 0; i < count; i++) {
            answers.add(new Answer(writer, QuestionTest.Q1, String.valueOf(i)));
        }

        return answers;
    }

}
