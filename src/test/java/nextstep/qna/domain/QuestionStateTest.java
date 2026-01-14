package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuestionStateTest {

    @Test
    @DisplayName("답변이 없는 경우 작성자는 질문을 삭제할 수 있다")
    void validateDeletePermission_NoAnswers_OwnerCanDelete() throws Exception {
        // given
        QuestionState state = new QuestionState(NsUserTest.JAVAJIGI);
        Answers answers = Answers.empty();

        // when & then
        state.validateDeletePermission(NsUserTest.JAVAJIGI, answers);
    }

    @Test
    @DisplayName("본인의 답변만 있는 경우 작성자는 질문을 삭제할 수 있다")
    void validateDeletePermission_OnlyOwnAnswers_OwnerCanDelete() throws Exception {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(2L, NsUserTest.JAVAJIGI, question, "answer2");
        Answers answers = Answers.of(Arrays.asList(answer1, answer2));
        QuestionState state = new QuestionState(NsUserTest.JAVAJIGI);

        // when & then
        state.validateDeletePermission(NsUserTest.JAVAJIGI, answers);
    }

    @Test
    @DisplayName("질문 작성자가 아닌 경우 삭제할 수 없다")
    void validateDeletePermission_NotOwner_ThrowsException() {
        // given
        QuestionState state = new QuestionState(NsUserTest.JAVAJIGI);
        Answers answers = Answers.empty();

        // when & then
        assertThatThrownBy(() -> state.validateDeletePermission(NsUserTest.SANJIGI, answers))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @Test
    @DisplayName("다른 사용자의 답변이 있는 경우 삭제할 수 없다")
    void validateDeletePermission_HasOtherUsersAnswers_ThrowsException() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(1L, NsUserTest.SANJIGI, question, "answer by other");
        Answers answers = Answers.of(Arrays.asList(answer));
        QuestionState state = new QuestionState(NsUserTest.JAVAJIGI);

        // when & then
        assertThatThrownBy(() -> state.validateDeletePermission(NsUserTest.JAVAJIGI, answers))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다");
    }

    @Test
    @DisplayName("isOwner는 작성자와 동일한 사용자일 때 true를 반환한다")
    void isOwner_SameUser_ReturnsTrue() {
        // given
        QuestionState state = new QuestionState(NsUserTest.JAVAJIGI);

        // when & then
        assertThat(state.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("isOwner는 작성자와 다른 사용자일 때 false를 반환한다")
    void isOwner_DifferentUser_ReturnsFalse() {
        // given
        QuestionState state = new QuestionState(NsUserTest.JAVAJIGI);

        // when & then
        assertThat(state.isOwner(NsUserTest.SANJIGI)).isFalse();
    }
}
