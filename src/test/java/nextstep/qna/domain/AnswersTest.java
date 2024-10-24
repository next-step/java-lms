package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    @Test
    @DisplayName("성공 - add 메서드가 답변을 추가한다.")
    void addTest() {
        Answers answers = new Answers();
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "답변1");
        answers.add(answer);

        assertThat(answers.getAnswers()).hasSize(1);
        assertThat(answers.getAnswers()).containsExactly(answer);
    }

    @Test
    @DisplayName("실패 - delete 메서드가 작성자가 아닌 다른 사람의 답변이 존재할 때 예외가 발생한다.")
    void throwExceptionDeleteTest() {
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "답변1");
        Answer answer2 = new Answer(NsUserTest.SANJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "답변1");
        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("성공 - delete 메서드가 answer 목록을 삭제한다.")
    void deleteTest() throws Exception {
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "답변1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "답변1");
        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);
        answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }
}