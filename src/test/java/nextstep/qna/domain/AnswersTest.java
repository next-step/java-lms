package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("모든 답변이 동일 사용자가 작성한 경우 validateAllOwnedBy는 예외를 던지지 않는다")
    void validateAllOwnedBy_AllOwnedBySameUser_NoException() throws Exception {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(2L, NsUserTest.JAVAJIGI, question, "answer2");
        Answers answers = Answers.of(Arrays.asList(answer1, answer2));

        // when & then
        answers.validateAllOwnedBy(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("다른 사용자가 작성한 답변이 포함되어 있으면 validateAllOwnedBy는 CannotDeleteException을 던진다")
    void validateAllOwnedBy_ContainsOtherUsersAnswer_ThrowsException() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(2L, NsUserTest.SANJIGI, question, "answer2");
        Answers answers = Answers.of(Arrays.asList(answer1, answer2));

        // when & then
        assertThatThrownBy(() -> answers.validateAllOwnedBy(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다");
    }

    @Test
    @DisplayName("deleteAll은 모든 답변을 삭제 상태로 변경한다")
    void deleteAll_MarksAllAnswersAsDeleted() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(2L, NsUserTest.JAVAJIGI, question, "answer2");
        Answers answers = Answers.of(Arrays.asList(answer1, answer2));

        // when
        answers.deleteAll();

        // then
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("createDeleteHistories는 모든 답변에 대한 DeleteHistory를 생성한다")
    void createDeleteHistories_CreatesHistoriesForAllAnswers() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer1");
        Answer answer2 = new Answer(2L, NsUserTest.SANJIGI, question, "answer2");
        Answers answers = Answers.of(Arrays.asList(answer1, answer2));
        LocalDateTime now = LocalDateTime.now();

        // when
        List<DeleteHistory> histories = answers.createDeleteHistories(now);

        // then
        assertThat(histories).hasSize(2);
        assertThat(histories.get(0).toString()).contains("ANSWER");
        assertThat(histories.get(1).toString()).contains("ANSWER");
    }

    @Test
    @DisplayName("isEmpty는 답변이 없을 때 true를 반환한다")
    void isEmpty_NoAnswers_ReturnsTrue() {
        // given
        Answers answers = Answers.empty();

        // when & then
        assertThat(answers.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("isEmpty는 답변이 있을 때 false를 반환한다")
    void isEmpty_HasAnswers_ReturnsFalse() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer");
        Answers answers = Answers.of(Arrays.asList(answer));

        // when & then
        assertThat(answers.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("add는 답변을 추가한다")
    void add_AddsAnswer() {
        // given
        Answers answers = Answers.empty();
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer");

        // when
        answers.add(answer);

        // then
        assertThat(answers.isEmpty()).isFalse();
    }
}
