package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.Fixtures.createAnswer;
import static nextstep.Fixtures.createQuestion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    @Test
    @DisplayName("답변이 없는 경우 삭제")
    void test01() throws CannotDeleteException {
        Question question = createQuestion(NsUserTest.JAVAJIGI);

        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다르면 에러 발생")
    void test02() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자 같은 경우 삭제")
    void test03() throws CannotDeleteException {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        question.addAnswer(createAnswer(NsUserTest.JAVAJIGI));

        question.delete(NsUserTest.JAVAJIGI);
        Answers answers = question.getAnswers();
        assertThat(question.isDeleted()).isEqualTo(true);
        assertThat(answers.getAnswers()).extracting("deleted").doesNotContain(false);
    }

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있으면 에러 발생")
    void test04() {
        Question question = createQuestion(NsUserTest.JAVAJIGI);
        question.addAnswer(createAnswer(NsUserTest.SANJIGI));

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
