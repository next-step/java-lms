package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @DisplayName("답변 객체에 답변 추가")
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
        assertThat(answers.getAnswers()).containsExactly(answer1, answer2);
    }

    @DisplayName("답변 작성자가 모두 일치하는지 여부 확인")
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
        assertThat(sameOwner).isTrue();
        assertThat(differentOwner).isFalse();
    }

    @DisplayName("모든 답변을 삭제하면 삭제이력 리턴")
    @Test
    void 모든_답변_삭제() throws CannotDeleteException {
        //given
        Answers answers = new Answers();
        Question question = new Question(NsUserTest.SANJIGI, "testTitle", "testContents");
        Answer answer1 = new Answer(NsUserTest.SANJIGI, question, "testAnswer1");
        Answer answer2 = new Answer(NsUserTest.SANJIGI, question, "testAnswer2");
        answers.add(answer1);
        answers.add(answer2);

        //when
        List<DeleteHistory> deleteHistories = answers.removeAll(NsUserTest.SANJIGI);

        //then
        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.SANJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), NsUserTest.SANJIGI, LocalDateTime.now())
        );
    }
}