package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionTest;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.QuestionTest.Q2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public Answers answers;

    @BeforeEach
    void setAnswers() {
        answers = new Answers();
    }

    @DisplayName("답변이 없는 지 확인")
    @Test
    public void isEmptyWhenEmptyAnswerThenIsTrue() {
        assertThat(answers.isEmpty()).isTrue();
    }

    @DisplayName("답변이 있을 경우 확인")
    @Test
    public void isEmptyWhenAddedAnswerThenIsFalse() {
        answers.add(A1, Q1);
        assertThat(answers.isEmpty()).isFalse();
    }

    @DisplayName("답변의 삭제 또한 삭제 상태(deleted)를 변경")
    @Test
    void delete() throws CannotDeleteException {
        assertThat(A1.isDeleted()).isFalse();

        answers.add(A1, Q1);

        answers.delete(Q1.getWriter());

        assertThat(A1.isDeleted()).isTrue();
    }

}
