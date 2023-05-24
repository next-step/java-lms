package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.Fixtures.AnswerFixtures.createAnswer1;
import static nextstep.Fixtures.AnswerFixtures.createAnswer2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

public class AnswersTest {

    @DisplayName("답변 추가")
    @Test
    void addAnswer() {
        Answers answers = new Answers();

        answers.addAnswer(createAnswer1(NsUserTest.JAVAJIGI));

        assertThat(answers.value()).hasSize(1);
    }

    @DisplayName("답변들 모두 삭제")
    @Test
    void deleteAllAnswers() {
        Answers answers = new Answers();
        answers.addAnswer(createAnswer1(NsUserTest.JAVAJIGI));
        answers.addAnswer(createAnswer2(NsUserTest.JAVAJIGI));

        List<DeleteHistory> deleteHistories = answers.deleteAll(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).hasSize(2)
                .extracting(DeleteHistory::getContentType, DeleteHistory::getDeletedBy)
                .containsExactly(
                        tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI),
                        tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI)
                );
    }

    @DisplayName("답변들 모두 삭제 시 작성자가 다를 시 에러")
    @Test
    void answerDeleteException() {
        Answers answers = new Answers();
        answers.addAnswer(createAnswer1(NsUserTest.SANJIGI));

        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
