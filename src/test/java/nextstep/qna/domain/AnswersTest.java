package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("답변 컬렉션 객체 테스트")
class AnswersTest {

    Question question;;
    Answers answers = new Answers();


    @BeforeEach
    void init() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    }

    @DisplayName("답변을 추가 할 수 있다")
    @Test
    void addAnswers() {
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer1"));
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer2"));
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer3"));

        assertThat(answers.countAnswer()).isEqualTo(3);
    }

    @DisplayName("답변의 작성자가 질문자와 같지 않으면 false 를 반환한다")
    @Test
    void isAllOwnerAnswerFalse() {
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer1"));
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer2"));
        answers.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "answer3"));

        boolean allOwnerAnswer = answers.isAllOwnerAnswer(NsUserTest.JAVAJIGI);

        assertThat(allOwnerAnswer).isFalse();
    }

    @DisplayName("답변의 작성자가 질문자와 같으면 true 를 반환한다")
    @Test
    void isAllOwnerAnswerTrue() {
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer1"));
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer2"));
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer3"));

        boolean allOwnerAnswer = answers.isAllOwnerAnswer(NsUserTest.SANJIGI);

        assertThat(allOwnerAnswer).isTrue();
    }

    @DisplayName("모든 답변을 삭제 할 수 있다")
    @Test
    void deleteAllAnswer() throws CannotDeleteException {
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer1"));
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer2"));
        answers.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer3"));

        List<DeleteHistory> deleteHistories = answers.deleteAllAnswer(NsUserTest.SANJIGI);
        assertThat(deleteHistories.size()).isEqualTo(3);
    }







}