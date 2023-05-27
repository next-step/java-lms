package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.fixtures.AnswerFixtures.*;
import static nextstep.fixtures.QuestionFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    @DisplayName("답변이 없는 경우 삭제")
    @Test
    void noAnswerDelete() throws CannotDeleteException {
        Question question = createQuestion(NsUserTest.JAVAJIGI);

        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isEqualTo(true);
    }

    @DisplayName("로그인 사용자와 질문한 사람이 다르면 에러 발생")
    @Test
    void answerDiffWriterException() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문자와 답변글의 모든 답변자 같은 경우 삭제")
    @Test
    void answerSameWriter() throws CannotDeleteException {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        question.addAnswer(createAnswer1(NsUserTest.JAVAJIGI));

        question.delete(NsUserTest.JAVAJIGI);
        Answers answers = question.value();
        assertThat(question.isDeleted()).isEqualTo(true);
        assertThat(answers.value()).extracting("deleted").doesNotContain(false);
    }

    @DisplayName("다른 사람이 쓴 답변이 있으면 에러 발생")
    @Test
    void deleteWhenAddAnswerException() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        question.addAnswer(createAnswer1(NsUserTest.SANJIGI));

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
