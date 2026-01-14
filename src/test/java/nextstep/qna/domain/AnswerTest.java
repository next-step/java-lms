package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete 메서드는 답변을 삭제 상태로 변경한다")
    void delete_MarksAnswerAsDeleted() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer contents");

        // when
        answer.delete();

        // then
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("isOwner는 작성자와 동일한 사용자일 때 true를 반환한다")
    void isOwner_SameUser_ReturnsTrue() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer contents");

        // when & then
        assertThat(answer.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("isOwner는 작성자와 다른 사용자일 때 false를 반환한다")
    void isOwner_DifferentUser_ReturnsFalse() {
        // given
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, question, "answer contents");

        // when & then
        assertThat(answer.isOwner(NsUserTest.SANJIGI)).isFalse();
    }
}

