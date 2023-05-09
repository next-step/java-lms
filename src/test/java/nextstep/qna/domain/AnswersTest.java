package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.Fixtures.createAnswer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("답변을 추가")
    void test01() {
        Answers answers = new Answers();

        answers.addAnswer(createAnswer(NsUserTest.JAVAJIGI));

        assertThat(answers.getAnswers()).hasSize(1);
    }

    @Test
    @DisplayName("답변들을 모두 삭제")
    void test02() {
        Answers answers = new Answers();
        answers.addAnswer(createAnswer(NsUserTest.JAVAJIGI));

        answers.deleteAll(NsUserTest.JAVAJIGI);

        assertThat(answers.getAnswers()).extracting("deleted").doesNotContain(false);
    }

    @Test
    @DisplayName("답변들을 모두 삭제 시 작성자가 다를 시 에러 발생")
    void test03() {
        Answers answers = new Answers();
        answers.addAnswer(createAnswer(NsUserTest.SANJIGI));

        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }


}
