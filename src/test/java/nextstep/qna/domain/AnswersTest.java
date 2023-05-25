package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Answer javajigiAnswer;

    @BeforeEach
    void setUp() {
        Question javajigiQuestion = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        javajigiAnswer = new Answer(NsUserTest.JAVAJIGI, javajigiQuestion, "Answers Contents1");
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.")
    void test01() {
        Answers answers = new Answers();
        answers.add(javajigiAnswer);
        assertThatNoException().isThrownBy(() -> answers.deleteAll(NsUserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같지 않은 경우 예외가 발생한다.")
    void test02() {
        Answers answers = new Answers();
        answers.add(javajigiAnswer);
        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
