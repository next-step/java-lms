package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.Fixtures.createAnswer;
import static org.assertj.core.api.Assertions.assertThat;

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
        answers.deleteAll();

        assertThat(answers.getAnswers()).extracting("deleted").doesNotContain(false);
    }


}
