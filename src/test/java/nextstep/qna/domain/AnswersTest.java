package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @DisplayName("답변 추가가 잘 되는지")
    @Test
    void addTest() {
        Answers answers = new Answers();
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "답변 내용");

        answers.add(answer);

        assertThat(answers.size()).isEqualTo(1);
    }

    @DisplayName("답변이 없는 경우 삭제 가능한지")
    @Test
    void isDeletableTest_WhenEmpty() {
        Answers answers = new Answers();

        assertThat(answers.isDeletable(NsUserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("모든 답변이 질문 작성자의 경우 삭제 가능한지")
    @Test
    void isDeletableTest_WhenAllAnswersFromOwner() {
        Answers answers = new Answers();
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "답변 내용1");
        Answer answer2 = new Answer(2L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "답변 내용2");

        answers.add(answer1);
        answers.add(answer2);

        assertThat(answers.isDeletable(NsUserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("다른 사용자가 답변을 작성한 경우 삭제 불가능한지")
    @Test
    void isDeletableTest_WhenNotAllAnswersFromOwner() {
        Answers answers = new Answers();
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "답변 내용1");
        Answer answer2 = new Answer(2L, NsUserTest.SANJIGI, QuestionTest.Q1, "답변 내용2");

        answers.add(answer1);
        answers.add(answer2);

        assertThat(answers.isDeletable(NsUserTest.JAVAJIGI)).isFalse();
    }
}
