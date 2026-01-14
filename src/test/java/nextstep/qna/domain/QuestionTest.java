package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("답변이 없는 질문을 작성자가 삭제하면 성공하고 DeleteHistory가 1개 반환된다")
    void deleteBy_NoAnswers_Success() throws Exception {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        LocalDateTime now = LocalDateTime.now();

        // when
        List<DeleteHistory> histories = question.deleteBy(NsUserTest.JAVAJIGI, now);

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(histories).hasSize(1);
        assertThat(histories.get(0).toString()).contains("QUESTION");
    }

    @Test
    @DisplayName("작성자 본인의 답변만 있는 질문을 삭제하면 성공하고 Question과 Answer의 DeleteHistory가 반환된다")
    void deleteBy_WithOwnAnswers_Success() throws Exception {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(2L, NsUserTest.JAVAJIGI, question, "answer2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);
        LocalDateTime now = LocalDateTime.now();

        // when
        List<DeleteHistory> histories = question.deleteBy(NsUserTest.JAVAJIGI, now);

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(histories).hasSize(3); // 질문 1개 + 답변 2개
    }

    @Test
    @DisplayName("질문 작성자가 아닌 사용자가 삭제하려고 하면 CannotDeleteException 발생")
    void deleteBy_NotOwner_ThrowsException() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        LocalDateTime now = LocalDateTime.now();

        // when & then
        assertThatThrownBy(() -> question.deleteBy(NsUserTest.SANJIGI, now))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @Test
    @DisplayName("다른 사람이 작성한 답변이 있는 질문을 삭제하려고 하면 CannotDeleteException 발생")
    void deleteBy_WithOtherUsersAnswers_ThrowsException() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(1L, NsUserTest.SANJIGI, question, "answer by other user");
        question.addAnswer(answer);
        LocalDateTime now = LocalDateTime.now();

        // when & then
        assertThatThrownBy(() -> question.deleteBy(NsUserTest.JAVAJIGI, now))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다");
    }

    @Test
    @DisplayName("isOwner는 작성자와 동일한 사용자일 때 true를 반환한다")
    void isOwner_SameUser_ReturnsTrue() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");

        // when & then
        assertThat(question.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("isOwner는 작성자와 다른 사용자일 때 false를 반환한다")
    void isOwner_DifferentUser_ReturnsFalse() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");

        // when & then
        assertThat(question.isOwner(NsUserTest.SANJIGI)).isFalse();
    }
}
