package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    void 모든답변_삭제() throws CannotDeleteException {
        Question q = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, q, "answer 2");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, q, "answer 3");
        Answer answer3 = new Answer(NsUserTest.JAVAJIGI, q, "answer 1");
        Answers answers = Answers.from(answer1, answer2, answer3);
        int expected = 3;

        assertThat(answers.deleteAll(NsUserTest.JAVAJIGI).size()).isEqualTo(expected);
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(answer3.isDeleted()).isTrue();
    }

    @Test
    void 답변중에_다른사람이_있으면_예외발생() throws CannotDeleteException {
        Question q = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, q, "answer 2");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, q, "answer 3");
        Answer answer3 = new Answer(NsUserTest.SANJIGI, q, "answer 1");
        Answers answers = Answers.from(answer1, answer2, answer3);

        assertThat(answer1.isDeleted()).isFalse();
        assertThat(answer2.isDeleted()).isFalse();
        assertThat(answer3.isDeleted()).isFalse();

        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}