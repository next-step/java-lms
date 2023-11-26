package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    @DisplayName("Answers 객체에 Answer를 추가한다.")
    @Test
    void 답변_추가() {
        //given
        Answers answers = new Answers();
        Question question = new Question(NsUserTest.SANJIGI, "testTitle", "testContents");
        Answer answer1 = new Answer(NsUserTest.SANJIGI, question, "testAnswer1");
        Answer answer2 = new Answer(NsUserTest.SANJIGI, question, "testAnswer2");

        //when
        answers.add(answer1);
        answers.add(answer2);

        //then
        Assertions.assertThat(answers.getAnswers()).containsExactly(answer1, answer2);
    }

    @DisplayName("답변 작성자가 모두 일치한다면 true, 아니면 false리턴.")
    @Test
    void 답변들_작성자_확인() {
        //given
        Answers answers = new Answers();
        Question question = new Question(NsUserTest.SANJIGI, "testTitle", "testContents");
        answers.add(new Answer(NsUserTest.SANJIGI, question, "testAnswer1"));
        answers.add(new Answer(NsUserTest.SANJIGI, question, "testAnswer2"));

        //when
        boolean sameOwner = answers.isOwner(NsUserTest.SANJIGI);
        boolean differentOwner = answers.isOwner(NsUserTest.JAVAJIGI);

        //then
        Assertions.assertThat(sameOwner).isTrue();
        Assertions.assertThat(differentOwner).isFalse();
    }
}