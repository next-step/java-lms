package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.fixtures.NsUserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    private static final Question Q1 = new Question(NsUserFixtures.TEACHER_JAVAJIGI_1L, "title1", "contents1");
    private static final Answer A1 = new Answer(NsUserFixtures.TEACHER_JAVAJIGI_1L, Q1, "Answers Contents1");
    private static final Answer A2 = new Answer(NsUserFixtures.TEACHER_SANJIGI_2L, Q1, "Answers Contents2");

    private static final Question Q2 = new Question(NsUserFixtures.TEACHER_SANJIGI_2L, "title2", "contents2");

    @Test
    @DisplayName("질문 작성자가 로그인한 사용자면 질문을 삭제 상태로 변경하고 해당 질문을 반환한다")
    void delete_success() throws CannotDeleteException {
        Question deletedQuestion = Q1.delete(NsUserFixtures.TEACHER_JAVAJIGI_1L);
        assertThat(deletedQuestion.isDeleted()).isTrue();

        Answers answers = deletedQuestion.getAnswers();
        for (Answer answer : answers) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }

    @Test
    @DisplayName("질문 작성자가 로그인한 사용자가 아니면 삭제할 수 없다는 예외를 던진다")
    void delete_different_questionWriter_loginUser_throwsException() {
        assertThatThrownBy(
                () -> Q2.delete(NsUserFixtures.TEACHER_JAVAJIGI_1L)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
